package io.github.philippparis.generator.processor;

import org.apache.commons.lang3.StringUtils;
import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.types.ObjectType;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;
import io.github.philippparis.util.Utils;

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

        final var parent = generator.processSchema(Utils.refToTypeId(schemaRef.get().getRef()), schemaRef.get());
        final var child = generator.processSchema(id, schemaObject.get());
        ((ObjectType)parent).addChild(child);
        return child;
    }
}
