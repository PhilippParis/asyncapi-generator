package io.github.philippparis.generator.processor;

import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.Repository;
import io.github.philippparis.generator.types.ObjectType;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;
import io.github.philippparis.util.Utils;

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
        final var model = repository.put(id, new ObjectType(schema, id, generator.getOptions()));
        for (final var oneOf : schema.getOneOf()) {
            model.addChild(generator.processSchema(Utils.getTypeId(oneOf), oneOf));
        }
        return model;
    }
}
