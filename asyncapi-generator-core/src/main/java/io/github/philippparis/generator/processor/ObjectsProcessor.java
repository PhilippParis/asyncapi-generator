package io.github.philippparis.generator.processor;

import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.Repository;
import io.github.philippparis.generator.types.ObjectType;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;
import io.github.philippparis.util.Utils;

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
