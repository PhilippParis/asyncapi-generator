package org.example.generator.processor;

import org.example.parser.model.Schema;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.Repository;
import org.example.generator.types.ObjectType;
import org.example.generator.types.Type;
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
        final var model = repository.put(id, new ObjectType(schema, id));
        for (final var oneOf : schema.getOneOf()) {
            model.addChild(generator.process(Utils.getTypeId(oneOf), oneOf));
        }
        return model;
    }
}
