package io.github.philippparis.generator;

import com.samskivert.mustache.Mustache;
import io.github.philippparis.generator.lambdas.MustacheLambda;
import io.github.philippparis.generator.model.GeneratedFile;
import io.github.philippparis.generator.model.Options;
import io.github.philippparis.generator.processor.AllOfProcessor;
import io.github.philippparis.generator.processor.CollectionsProcessor;
import io.github.philippparis.generator.processor.EnumProcessor;
import io.github.philippparis.generator.processor.ObjectsProcessor;
import io.github.philippparis.generator.processor.OneOfProcessor;
import io.github.philippparis.generator.processor.PrimitivesProcessor;
import io.github.philippparis.generator.processor.Processor;
import io.github.philippparis.generator.processor.RefProcessor;
import io.github.philippparis.util.PathUtils;
import io.github.philippparis.util.Utils;
import org.apache.commons.io.IOUtils;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.AsyncApiDocument;
import io.github.philippparis.parser.model.Message;
import io.github.philippparis.parser.model.Schema;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AsyncApiGenerator {

    private final Repository repository = new Repository();
    private final List<Processor> processors = List.of( new PrimitivesProcessor(),
                                                        new ObjectsProcessor(repository),
                                                        new CollectionsProcessor(),
                                                        new AllOfProcessor(),
                                                        new OneOfProcessor(repository),
                                                        new EnumProcessor(repository),
                                                        new RefProcessor());

    private final AsyncApiDocument document;

    private final Options options;

    public AsyncApiGenerator(final AsyncApiDocument document, Options options) {
        this.document = document;
        this.options = options;
    }

    public List<GeneratedFile> exec() {
        for (final var entry : document.getComponents().getSchemas().entrySet()) {
            processSchema(entry.getKey(), entry.getValue());
        }
        for (final var entry : document.getComponents().getMessages().entrySet()) {
            processMessage(entry.getKey(), entry.getValue());
        }
        return generateModels();
    }

    private List<GeneratedFile> generateModels() {
        final var files = new ArrayList<GeneratedFile>();
        for (final var type : repository.getAll()) {
            if (type.getTemplate().isEmpty()) {
                continue;
            }
            final var content = applyTemplate(type, type.getTemplate().get());
            files.add(new GeneratedFile(PathUtils.getModelPath(options, type), content));
        }
        return files;
    }

    private String applyTemplate(final Type type, final String templateName) {
        try {
            final var template = IOUtils.resourceToString("/templates/" + templateName, StandardCharsets.UTF_8);
            return Mustache.compiler().escapeHTML(false).compile(template).execute(getTemplateContext(type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Type processSchema(final String id, final Schema schema) {
        for (final var processor : processors) {
            if (processor.check(schema)) {
                return processor.process(this, id, schema);
            }
        }
        throw new IllegalStateException("failed to process schema: " + schema);
    }

    public Options getOptions() {
        return options;
    }

    private void processMessage(final String id, final Message message) {
        if (message.getPayload() != null && message.getPayload().getRef() == null) {
            processSchema(id, message.getPayload());
        }
        if (message.getHeaders() != null && message.getHeaders().getRef() == null) {
            processSchema(id + "Headers", message.getHeaders());
        }
    }

    public Schema getSchemaByRef(final String ref) {
        return document.getComponents().getSchemas().get(Utils.refToTypeId(ref));
    }

    private Map<String, Object> getTemplateContext(final Type type) {
        return Map.of(
                "PascalCase", new MustacheLambda.PascalCase(),
                "CamelCase", new MustacheLambda.CamelCase(),
                "options", options,
                "type", type);
    }

}
