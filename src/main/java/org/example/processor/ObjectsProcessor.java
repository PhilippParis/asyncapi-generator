package org.example.processor;

import org.example.Repository;
import org.example.model.Schema;
import org.example.util.Utils;
import org.example.AsyncApiGenerator;
import org.example.types.ObjectType;
import org.example.types.Type;

public class ObjectsProcessor extends Processor {

    private final Repository repository;

    public ObjectsProcessor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(Schema schema) {
        return "object".equals(schema.getType());
    }

    @Override
    public Type process(final AsyncApiGenerator generator, final String id, final Schema schema) {
        if (repository.contains(id)) {
            return repository.get(id);
        }
        final var model = repository.put(id, new ObjectType(schema, id));
        for (final var property : schema.getProperties().entrySet()) {
            model.addProperty(property.getKey(), generator.process(Utils.getTypeId(property.getValue()), property.getValue()));
        }
        return model;
    }


}
