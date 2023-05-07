package org.example.parser.model;

import java.util.HashMap;
import java.util.Map;

public class Components {

    private Map<String, Schema> schemas = new HashMap<>();

    private Map<String, Message> messages = new HashMap<>();

    public Map<String, Schema> getSchemas() {
        return schemas;
    }

    public Map<String, Message> getMessages() {
        return messages;
    }
}
