package org.example.generator.processor;

import org.example.parser.model.Schema;
import org.apache.commons.lang3.StringUtils;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.types.ObjectType;
import org.example.generator.types.Type;
import org.example.util.Utils;

public class AllOfProcessor extends Processor {

    @Override
    public boolean check(Schema schema) {
        return !schema.getAllOf().isEmpty();
    }

    @Override
    public Type process(AsyncApiGenerator generator, String id, Schema schema) {
        if (schema.getAllOf().size() != 2) {
            throw new IllegalStateException("allOf must require exactly 2 items");
        }
        final var schemaRef = schema.getAllOf().stream().filter(i -> StringUtils.isNotEmpty(i.getRef())).findAny();
        final var schemaObject = schema.getAllOf().stream().filter(i -> "object".equals(i.getType())).findAny();

        if (schemaRef.isEmpty() || schemaObject.isEmpty()) {
            throw new IllegalArgumentException("allOf must require one reference and one anon object schema");
        }

        final var parent = generator.process(Utils.refToTypeId(schemaRef.get().getRef()), schemaRef.get());
        final var child = generator.process(id, schemaObject.get());
        ((ObjectType)parent).addChild(child);
        return child;
    }
}
