package org.example.parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AsyncApiDocument {

    @JsonProperty("asyncapi")
    private String version;
    @JsonProperty("info")
    private Info info;
    @JsonProperty("components")
    private Components components;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

}
