package org.example.generator.types;

import org.apache.commons.lang3.StringUtils;
import org.example.generator.model.Options;
import org.example.parser.model.Schema;
import org.example.util.CaseUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnumType extends Type {

    private final String id;
    private final Options options;

    public EnumType(final Schema schema, final String id, final Options options) {
        super(schema);
        this.id = id;
        this.options = options;
    }

    @Override
    public String getTypeName() {
        return StringUtils.appendIfMissing(CaseUtils.toPascalCase(id), options.getModelNameSuffix());
    }

    @Override
    public String getId() {
        return id;
    }

    public List<String> getEnumValues() {
        return schema.getEnums().stream().map(CaseUtils::toSnakeCase).map(String::toUpperCase).collect(Collectors.toList());
    }

    @Override
    public Optional<String> getTemplate() {
        return Optional.of("enum.mustache");
    }
}
