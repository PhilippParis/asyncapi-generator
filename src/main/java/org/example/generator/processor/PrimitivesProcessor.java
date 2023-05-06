package org.example.generator.processor;

import org.apache.commons.lang3.StringUtils;
import org.example.parser.model.Schema;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.types.Primitives;
import org.example.generator.types.Type;

import java.util.Set;

public class PrimitivesProcessor extends Processor {

    private static final Set<String> PRIMITIVE_TYPES = Set.of("integer", "string", "number", "boolean");

    @Override
    public boolean check(final Schema schema) {
        return schema.getEnums().isEmpty() && schema.getType() != null && PRIMITIVE_TYPES.contains(schema.getType());
    }

    @Override
    public Type process(final AsyncApiGenerator generator, final String id, final Schema schema) {
        return switch (schema.getType()) {
            case "integer" -> processInteger(schema);
            case "string" -> processString(schema);
            case "number" -> processNumber(schema);
            case "boolean" -> new Primitives.BooleanType(schema);
            default -> throw new IllegalArgumentException("primitive type not supported: " + schema.getType());
        };
    }

    private Type processInteger(final Schema schema) {
        if ("int64".equals(schema.getFormat())) {
            return new Primitives.LongType(schema);
        }
        return new Primitives.IntType(schema);
    }

    private Type processNumber(final Schema schema) {
        if ("float".equals(schema.getFormat())) {
            return new Primitives.FloatType(schema);
        }
        return new Primitives.DoubleType(schema);
    }

    private Type processString(final Schema schema) {
        if (schema.getFormat() == null) {
            return new Primitives.StringType(schema);
        }
        return switch (schema.getFormat()) {
            case "uuid" -> new Primitives.UUIDType(schema);
            case "date" -> new Primitives.LocalDateType(schema);
            case "date-time" -> new Primitives.OffsetDateTime(schema);
            default -> new Primitives.StringType(schema);
        };
    }

}
