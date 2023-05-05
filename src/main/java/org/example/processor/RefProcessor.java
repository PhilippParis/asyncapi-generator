package org.example.processor;

import org.example.model.Schema;
import org.apache.commons.lang3.StringUtils;
import org.example.AsyncApiGenerator;
import org.example.types.Type;
import org.example.util.Utils;

public class RefProcessor extends Processor{
    @Override
    public boolean check(Schema schema) {
        return StringUtils.isNotEmpty(schema.getRef());
    }

    @Override
    public Type process(AsyncApiGenerator generator, String id, Schema schema) {
        final var schemaRef = generator.getSchemaByRef(schema.getRef());
        return generator.process(Utils.refToTypeId(schema.getRef()), schemaRef);
    }
}
