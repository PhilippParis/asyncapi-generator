package org.example.generator.types;

import org.example.parser.model.Schema;

import java.util.*;

public class ObjectType extends Type {

    private final String id;
    private final List<Type> children = new ArrayList<>();
    private final Map<String, Type> properties = new HashMap<>();
    private Type parent;


    public ObjectType(final Schema schema, final String id) {
        super(schema);
        this.id = id;
    }

    public void addProperty(final String name, final Type objectType) {
        properties.put(name, objectType);
    }

    public void addChild(final Type type) {
        children.add(type);
        if (type instanceof ObjectType) {
            ((ObjectType) type).parent = this;
        }
    }

    @Override
    public String getTypeName() {
        return id;
    }

    @Override
    public Optional<String> getTemplate() {
        return Optional.of("model.mustache");
    }
}
