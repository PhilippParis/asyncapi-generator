package org.example.generator.processor;

import org.example.parser.model.Schema;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.types.Primitives;
import org.example.generator.types.Type;

import java.util.Set;

public class PrimitivesProcessor extends Processor {

    private static final Set<String> PRIMITIVE_TYPES = Set.of("integer", "string", "number", "boolean");

    @Override
    public boolean check(Schema schema) {
        return schema.getEnums().isEmpty() && schema.getType() != null && PRIMITIVE_TYPES.contains(schema.getType());
    }

    @Override
    public Type process(final AsyncApiGenerator generator, String id, Schema schema) {
        return switch (schema.getType()) {
            case "integer" -> new Primitives.IntType(schema);
            case "string" -> new Primitives.StringType(schema);
            case "number" -> new Primitives.NumberType(schema);
            case "boolean" -> new Primitives.BooleanType(schema);
            default -> throw new IllegalArgumentException("primitive type not supported: " + schema.getType());
        };
    }


}
