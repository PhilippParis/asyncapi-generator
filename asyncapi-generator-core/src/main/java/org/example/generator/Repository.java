package org.example.generator;

import org.example.generator.types.Type;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Repository {

    private final Map<String, Type> objects = new HashMap<>();

    public <T extends Type> T put(String id, T type) {
        objects.put(id, type);
        return type;
    }

    public Type get(String id) {
        return objects.get(id);
    }

    public boolean contains(String id) {
        return objects.containsKey(id);
    }

    public Collection<Type> getAll() {
        return objects.values();
    }
}
