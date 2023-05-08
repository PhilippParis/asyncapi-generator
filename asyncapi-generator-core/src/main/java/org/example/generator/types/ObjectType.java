package org.example.generator.types;

import org.example.generator.model.ModelProperty;
import org.example.parser.model.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectType extends Type {

    private final String id;
    private final List<Type> children = new ArrayList<>();
    private final List<ModelProperty> properties = new ArrayList<>();
    private Type parent;


    public ObjectType(final Schema schema, final String id) {
        super(schema);
        this.id = id;
    }

    public void addProperty(final String name, final Type objectType) {
        properties.add(new ModelProperty(name, objectType, schema.getRequired().contains(name)));
    }

    public List<ModelProperty> getProperties() {
        return properties;
    }

    public void addChild(final Type type) {
        children.add(type);
        if (type instanceof ObjectType) {
            ((ObjectType) type).parent = this;
        }
    }

    public String getDiscriminator() {
        return Optional.ofNullable(schema.getDiscriminator()).orElse("@class");
    }

    @Override
    public Set<String> getImports() {
        return properties.stream().flatMap(i -> i.getPropertyType().getImports().stream()).collect(Collectors.toSet());
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
