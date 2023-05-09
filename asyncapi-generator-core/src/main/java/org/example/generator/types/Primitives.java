package org.example.generator.types;

import org.example.parser.model.Schema;

import java.util.Set;

public class Primitives {

    public static class IntType extends Type {
        public IntType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "Integer";
        }
    }

    public static class LongType extends Type {
        public LongType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "Long";
        }
    }

    public static class StringType extends Type {
        public StringType(Schema schema) {
            super(schema);
        }

        @Override
        public String getTypeName() {
            return "String";
        }
    }

    public static class LocalDateType extends Type {
        public LocalDateType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "LocalDate";
        }
        @Override
        public Set<String> getImports() {
            return Set.of("java.time.LocalDate");
        }
    }

    public static class OffsetDateTime extends Type {
        public OffsetDateTime(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "OffsetDateTime";
        }
        @Override
        public Set<String> getImports() {
            return Set.of("java.time.OffsetDateTime");
        }
    }

    public static class UUIDType extends Type {
        public UUIDType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "UUID";
        }
        @Override
        public Set<String> getImports() {
            return Set.of("java.util.UUID");
        }
    }

    public static class BigDecimalType extends Type {
        public BigDecimalType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "BigDecimal";
        }
        @Override
        public Set<String> getImports() {
            return Set.of("java.math.BigDecimal");
        }
    }

    public static class DoubleType extends Type {
        public DoubleType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "Double";
        }
    }

    public static class FloatType extends Type {
        public FloatType(Schema schema) {
            super(schema);
        }
        @Override
        public String getTypeName() {
            return "Float";
        }
    }

    public static class BooleanType extends Type {
        public BooleanType(Schema schema) {
            super(schema);
        }

        @Override
        public String getTypeName() {
            return "Boolean";
        }
    }

}