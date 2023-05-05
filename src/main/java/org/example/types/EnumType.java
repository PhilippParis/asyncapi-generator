package org.example.types;

import org.example.model.Schema;
import org.example.util.CaseUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnumType extends Type {

    private String id;

    public EnumType(final Schema schema, final String id) {
        super(schema);
        this.id = id;
    }

    @Override
    public String getTypeName() {
        return CaseUtils.toPascalCase(id);
    }

    public List<String> getEnumValues() {
        return schema.getEnums().stream().map(CaseUtils::toSnakeCase).map(String::toUpperCase).collect(Collectors.toList());
    }

    @Override
    public Optional<String> getTemplate() {
        return Optional.of("enum.mustache");
    }
}
