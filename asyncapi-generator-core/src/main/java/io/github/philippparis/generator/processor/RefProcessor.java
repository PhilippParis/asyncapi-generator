package io.github.philippparis.generator.processor;

import org.apache.commons.lang3.StringUtils;
import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;
import io.github.philippparis.util.Utils;

public class RefProcessor extends Processor{
    @Override
    public boolean check(Schema schema) {
        return StringUtils.isNotEmpty(schema.getRef());
    }

    @Override
    public Type process(AsyncApiGenerator generator, String id, Schema schema) {
        final var schemaRef = generator.getSchemaByRef(schema.getRef());
        return generator.processSchema(Utils.refToTypeId(schema.getRef()), schemaRef);
    }
}
