package org.example.generator.types;

import org.example.parser.model.Schema;

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

    public static class StringType extends Type { // TODO support UUID, LocalDateTime
        public StringType(Schema schema) {
            super(schema);
        }

        @Override
        public String getTypeName() {
            return "String";
        }
    }

    public static class NumberType extends Type {  // TODO support BigDecimal, BigInteger
        public NumberType(Schema schema) {
            super(schema);
        }

        @Override
        public String getTypeName() {
            return "Double";
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
