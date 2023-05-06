package org.example.generator.types;

import org.example.parser.model.Schema;

import java.util.Optional;

public class Collections {

    public static class ListType extends Type {
        private final Type child;

        public ListType(final Schema schema, final Type child) {
            super(schema);
            this.child = child;
        }

        @Override
        public Optional<String> getImport() {
            return Optional.of("java.util.ArrayList");
        }
        @Override
        public String getTypeName() {
            return "ArrayList<" + child.getTypeName() + ">";
        }
    }

    public static class MapType extends Type {
        private final Type child;

        public MapType(final Schema schema, final Type child) {
            super(schema);
            this.child = child;
        }

        @Override
        public Optional<String> getImport() {
            return Optional.of("java.util.HashMap");
        }
        @Override
        public String getTypeName() {
            return "HashMap<" + child.getTypeName() + ">";
        }
    }

}

