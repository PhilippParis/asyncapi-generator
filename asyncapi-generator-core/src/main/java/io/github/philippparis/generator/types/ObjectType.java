package io.github.philippparis.generator.types;

import io.github.philippparis.generator.model.ModelProperty;
import io.github.philippparis.generator.model.Options;
import org.apache.commons.lang3.StringUtils;
import io.github.philippparis.parser.model.Schema;
import io.github.philippparis.util.CaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectType extends Type {

    private final String id;
    private final List<Type> children = new ArrayList<>();
    private final List<ModelProperty> properties = new ArrayList<>();
    private final Options options;
    private Type parent;


    public ObjectType(final Schema schema, final String id, final Options options) {
        super(schema);
        this.id = id;
        this.options = options;
    }

    public void addProperty(final String name, final Type objectType) {
        properties.add(new ModelProperty(name, objectType, schema.getRequired().contains(name)));
    }

    public List<ModelProperty> getProperties() {
        if (parent == null || !(parent instanceof ObjectType)) {
            return properties;
        }
        return properties.stream()
                .filter(i -> !i.getPropertyName().equals(((ObjectType) parent).getDiscriminator()))
                .collect(Collectors.toList());
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
        return StringUtils.appendIfMissing(CaseUtils.toPascalCase(id), options.getModelNameSuffix());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Optional<String> getTemplate() {
        return Optional.of("model.mustache");
    }

    @Override
    public String getSubTypeName() {
        if (parent == null || !(parent instanceof ObjectType)) {
            return getId();
        }
        return properties.stream()
                .filter(i -> i.getPropertyName().equals(((ObjectType) parent).getDiscriminator()))
                .map(ModelProperty::getPropertyType)
                .map(i -> i.schema.getConstant())
                .filter(StringUtils::isNotBlank)
                .findAny()
                .orElse(getId());
    }
}
