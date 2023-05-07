package org.example.parser.model;

public class Message {

    private String name;
    private String title;
    private String summary;
    private String description;


    private String correlationId;
    private String contentType;
    private String schemaFormat;


    private Schema headers;
    private Schema payload;


    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getContentType() {
        return contentType;
    }

    public String getSchemaFormat() {
        return schemaFormat;
    }

    public Schema getHeaders() {
        return headers;
    }

    public Schema getPayload() {
        return payload;
    }
}
