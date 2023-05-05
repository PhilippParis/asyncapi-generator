package org.example.processor;

import org.example.model.Schema;
import org.example.AsyncApiGenerator;
import org.example.Repository;
import org.example.types.ObjectType;
import org.example.types.Type;
import org.example.util.Utils;

public class OneOfProcessor extends Processor {

    private final Repository repository;

    public OneOfProcessor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(Schema schema) {
        return !schema.getOneOf().isEmpty();
    }

    @Override
    public Type process(AsyncApiGenerator generator, String id, Schema schema) {
        final var model = repository.put(id, new ObjectType(schema, id)); // TODO interface type?
        for (final var oneOf : schema.getOneOf()) {
            model.addChild(generator.process(Utils.getTypeId(oneOf), oneOf));
        }
        return model;
    }
}
