package org.example.generator.types;

import org.example.parser.model.Schema;

import java.util.Optional;

public abstract class Type {

    protected final Schema schema;

    public Type(Schema schema) {
        this.schema = schema;
    }

    public abstract String getTypeName();

    public Optional<String> getImport() {
        return Optional.empty();
    }

    public Optional<String> getTemplate() {
        return Optional.empty();
    }

}