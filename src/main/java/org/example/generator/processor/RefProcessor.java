package org.example.generator.processor;

import org.apache.commons.lang3.StringUtils;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.types.Type;
import org.example.parser.model.Schema;
import org.example.util.Utils;

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
