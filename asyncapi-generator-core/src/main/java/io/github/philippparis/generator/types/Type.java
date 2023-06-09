package io.github.philippparis.generator.types;

import io.github.philippparis.parser.model.Schema;

import java.util.Optional;
import java.util.Set;

public abstract class Type {

    protected final Schema schema;

    public Type(Schema schema) {
        this.schema = schema;
    }

    public abstract String getTypeName();

    public Optional<String> getInit() {
        return Optional.empty();
    }

    public String getId() {
        return null;
    }

    public Set<String> getImports() {
        return Set.of();
    }

    public Optional<String> getTemplate() {
        return Optional.empty();
    }

    public boolean isPassword() {
        return "password".equals(schema.getFormat());
    }

    public String getSubTypeName() {
        return getId();
    }

}
