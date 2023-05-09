package org.example.generator.types;

import org.example.parser.model.Schema;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Collections {

    public static class ListType extends Type {
        private final Type child;

        public ListType(final Schema schema, final Type child) {
            super(schema);
            this.child = child;
        }

        @Override
        public Set<String> getImports() {
            final var imports = new HashSet<String>();
            imports.add("java.util.ArrayList");
            imports.addAll(child.getImports());
            return imports;
        }
        @Override
        public String getTypeName() {
            return "ArrayList<" + child.getTypeName() + ">";
        }

        @Override
        public Optional<String> getInit() {
            return Optional.of("new ArrayList<" + child.getTypeName() + ">()");
        }
    }

    public static class MapType extends Type {
        private final Type child;

        public MapType(final Schema schema, final Type child) {
            super(schema);
            this.child = child;
        }

        @Override
        public Set<String> getImports() {
            final var imports = new HashSet<String>();
            imports.add("java.util.HashMap");
            imports.addAll(child.getImports());
            return imports;
        }
        @Override
        public String getTypeName() {
            return "HashMap<" + child.getTypeName() + ">";
        }

        @Override
        public Optional<String> getInit() {
            return Optional.of("new HashMap<" + child.getTypeName() + ">()");
        }
    }

}

