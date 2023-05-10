package io.github.philippparis.generator.model;

import io.github.philippparis.generator.types.Type;
import io.github.philippparis.util.CaseUtils;

public class ModelProperty {

    private String propertyId;

    private Type propertyType;

    private boolean required;

    public ModelProperty(String propertyId, Type propertyType, boolean required) {
        this.propertyId = propertyId;
        this.propertyType = propertyType;
        this.required = required;
    }

    public String getPropertyName() {
        return CaseUtils.toCamelCase(propertyId);
    }

    public String getPropertyId() {
        return propertyId;
    }

    public Type getPropertyType() {
        return propertyType;
    }

    public boolean isRequired() {
        return required;
    }
}
