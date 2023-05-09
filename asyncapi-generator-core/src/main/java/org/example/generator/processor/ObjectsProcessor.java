package org.example.generator.processor;

import org.example.generator.AsyncApiGenerator;
import org.example.generator.Repository;
import org.example.generator.types.ObjectType;
import org.example.generator.types.Type;
import org.example.parser.model.Schema;
import org.example.util.Utils;

import java.util.Objects;

public class ObjectsProcessor extends Processor {

    private final Repository repository;

    public ObjectsProcessor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(Schema schema) {
        return "object".equals(schema.getType()) && Objects.isNull(schema.getAdditionalProperties());
    }

    @Override
    public Type process(final AsyncApiGenerator generator, final String id, final Schema schema) {
        if (repository.contains(id)) {
            return repository.get(id);
        }
        final var model = repository.put(id, new ObjectType(schema, id, generator.getOptions()));
        for (final var property : schema.getProperties().entrySet()) {
            model.addProperty(property.getKey(), generator.processSchema(Utils.getTypeId(property.getValue()), property.getValue()));
        }
        return model;
    }


}
