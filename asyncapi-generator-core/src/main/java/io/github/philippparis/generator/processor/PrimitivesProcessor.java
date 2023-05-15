package io.github.philippparis.generator.processor;

import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.types.Primitives;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;

import java.util.Set;

public class PrimitivesProcessor extends Processor {

    private static final Set<String> PRIMITIVE_TYPES = Set.of("integer", "string", "number", "boolean");

    @Override
    public boolean check(final Schema schema) {
        return schema.getEnums().isEmpty() && schema.getType() != null && PRIMITIVE_TYPES.contains(schema.getType());
    }

    @Override
    public Type process(final AsyncApiGenerator generator, final String id, final Schema schema) {
        switch (schema.getType()) {
            case "integer":
                return processInteger(schema);
            case "string":
                return processString(schema);
            case "number":
                return processNumber(schema);
            case "boolean":
                return new Primitives.BooleanType(schema);
            default:
                throw new IllegalArgumentException("primitive type not supported: " + schema.getType());
        }
    }

    private Type processInteger(final Schema schema) {
        if ("int32".equals(schema.getFormat())) {
            return new Primitives.IntType(schema);
        }
        if ("int64".equals(schema.getFormat())) {
            return new Primitives.LongType(schema);
        }
        return new Primitives.BigIntegerType(schema);
    }

    private Type processNumber(final Schema schema) {
        if ("float".equals(schema.getFormat())) {
            return new Primitives.FloatType(schema);
        }
        if ("double".equals(schema.getFormat())) {
            return new Primitives.DoubleType(schema);
        }
        return new Primitives.BigDecimalType(schema);
    }

    private Type processString(final Schema schema) {
        if (schema.getFormat() == null) {
            return new Primitives.StringType(schema);
        }
        switch (schema.getFormat()) {
            case "uuid":
                return new Primitives.UUIDType(schema);
            case "date":
                return new Primitives.LocalDateType(schema);
            case "date-time":
                return new Primitives.OffsetDateTime(schema);
            default:
                return new Primitives.StringType(schema);
        }
    }

}
