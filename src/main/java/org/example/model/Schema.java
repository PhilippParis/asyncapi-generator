package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Schema {

    // metadata
    private String title;
    private String description;
    private List<String> examples = new ArrayList<>();
    @JsonProperty("$ref")
    private String ref;

    // type information
    private String type;
    private String format;
    @JsonProperty("enum")
    private List<String> enums = new ArrayList<>();
    @JsonProperty("const")
    private String constant;
    private Schema additionalProperties;
    private String additionalItems; // TODO
    private Schema items;
    private String discriminator;
    private List<Schema> allOf = new ArrayList<>();
    private List<Schema> oneOf = new ArrayList<>();
    private Map<String, Schema> properties = new HashMap<>();
    private List<String> required = new ArrayList<>();

    // constraints
    private Double multipleOf;
    private Double maximum;
    private Double exclusiveMaximum;
    private Double minimum;
    private Double exclusiveMinimum;
    private Integer maxLength;
    private Integer minLength;
    private String pattern;
    private Integer maxItems;
    private Integer minItems;
    private Boolean uniqueItems;
    private Integer minProperties;
    private Integer maxProperties;
    private Boolean readOnly;
    private Boolean writeOnly;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getEnums() {
        return enums;
    }

    public void setEnums(List<String> enums) {
        this.enums = enums;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public Schema getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Schema additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(String additionalItems) {
        this.additionalItems = additionalItems;
    }

    public Schema getItems() {
        return items;
    }

    public void setItems(Schema items) {
        this.items = items;
    }

    public List<Schema> getAllOf() {
        return allOf;
    }

    public void setAllOf(List<Schema> allOf) {
        this.allOf = allOf;
    }

    public List<Schema> getOneOf() {
        return oneOf;
    }

    public void setOneOf(List<Schema> oneOf) {
        this.oneOf = oneOf;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Map<String, Schema> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Schema> properties) {
        this.properties = properties;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public Double getMultipleOf() {
        return multipleOf;
    }

    public void setMultipleOf(Double multipleOf) {
        this.multipleOf = multipleOf;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getExclusiveMaximum() {
        return exclusiveMaximum;
    }

    public void setExclusiveMaximum(Double exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getExclusiveMinimum() {
        return exclusiveMinimum;
    }

    public void setExclusiveMinimum(Double exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }

    public Integer getMinItems() {
        return minItems;
    }

    public void setMinItems(Integer minItems) {
        this.minItems = minItems;
    }

    public Boolean getUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public Integer getMinProperties() {
        return minProperties;
    }

    public void setMinProperties(Integer minProperties) {
        this.minProperties = minProperties;
    }

    public Integer getMaxProperties() {
        return maxProperties;
    }

    public void setMaxProperties(Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getWriteOnly() {
        return writeOnly;
    }

    public void setWriteOnly(Boolean writeOnly) {
        this.writeOnly = writeOnly;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Schema.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("examples=" + examples)
                .add("ref='" + ref + "'")
                .add("type='" + type + "'")
                .add("format='" + format + "'")
                .add("enums=" + enums)
                .add("constant='" + constant + "'")
                .add("additionalProperties=" + additionalProperties)
                .add("additionalItems='" + additionalItems + "'")
                .add("items=" + items)
                .add("discriminator='" + discriminator + "'")
                .add("allOf=" + allOf)
                .add("oneOf=" + oneOf)
                .add("properties=" + properties)
                .add("required=" + required)
                .add("multipleOf=" + multipleOf)
                .add("maximum=" + maximum)
                .add("exclusiveMaximum=" + exclusiveMaximum)
                .add("minimum=" + minimum)
                .add("exclusiveMinimum=" + exclusiveMinimum)
                .add("maxLength=" + maxLength)
                .add("minLength=" + minLength)
                .add("pattern='" + pattern + "'")
                .add("maxItems=" + maxItems)
                .add("minItems=" + minItems)
                .add("uniqueItems=" + uniqueItems)
                .add("minProperties=" + minProperties)
                .add("maxProperties=" + maxProperties)
                .add("readOnly=" + readOnly)
                .add("writeOnly=" + writeOnly)
                .toString();
    }
}
