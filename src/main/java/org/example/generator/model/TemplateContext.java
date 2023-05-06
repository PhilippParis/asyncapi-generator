package org.example.generator.model;

import org.example.generator.types.Type;

public class TemplateContext {

    private final Options options;

    private final Type type;

    public TemplateContext(Options options, Type type) {
        this.options = options;
        this.type = type;
    }

    public Options getOptions() {
        return options;
    }

    public Type getType() {
        return type;
    }
}
