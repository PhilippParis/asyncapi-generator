package org.example.generator.model;

import org.example.generator.types.Type;

public class ModelProperty {

    private String propertyName;

    private Type propertyType;

    private boolean required;

    public ModelProperty(String propertyName, Type propertyType, boolean required) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.required = required;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Type getPropertyType() {
        return propertyType;
    }

    public boolean isRequired() {
        return required;
    }
}
