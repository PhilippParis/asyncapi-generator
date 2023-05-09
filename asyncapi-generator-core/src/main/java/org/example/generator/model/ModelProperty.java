package org.example.generator.model;

import org.example.generator.types.Type;
import org.example.util.CaseUtils;

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
