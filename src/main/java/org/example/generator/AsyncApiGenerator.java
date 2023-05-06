package org.example.generator;

import com.samskivert.mustache.Mustache;
import org.apache.commons.io.IOUtils;
import org.example.generator.model.Options;
import org.example.generator.model.TemplateContext;
import org.example.generator.processor.*;
import org.example.parser.model.AsyncApiDocument;
import org.example.parser.model.Schema;
import org.example.generator.types.Type;
import org.example.util.Utils;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsyncApiGenerator {

    private final Repository repository = new Repository();
    private final List<Processor> processors = List.of(new PrimitivesProcessor(),
            new ObjectsProcessor(repository),
            new CollectionsProcessor(),
            new AllOfProcessor(),
            new OneOfProcessor(repository), new EnumProcessor(repository),
            new RefProcessor());

    private final AsyncApiDocument document;

    private final Options options;

    public AsyncApiGenerator(final AsyncApiDocument document, Options options) {
        this.document = document;
        this.options = options;
    }

    public void exec() {
        for (final var entry : document.getComponents().getSchemas().entrySet()) {
            process(entry.getKey(), entry.getValue());
        }
    }

    public Set<String> generateModels() {
        final var files = new HashSet<String>();
        for (final var type : repository.getAll()) {
            type.getTemplate().ifPresent(i -> files.add(applyTemplate(type, i)));
        }
        return files;
    }

    private String applyTemplate(final Type type, final String templateName) {
        try {
            final var template = IOUtils.resourceToString("/templates/" + templateName, StandardCharsets.UTF_8);
            return Mustache.compiler().compile(template).execute(new TemplateContext(options, type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Type process(final String id, final Schema schema) {
        for (final var processor : processors) {
            if (processor.check(schema)) {
                return processor.process(this, id, schema);
            }
        }
        throw new IllegalStateException("failed to process schema: " + schema);
    }

    public Schema getSchemaByRef(final String ref) {
        return document.getComponents().getSchemas().get(Utils.refToTypeId(ref));
    }

}
