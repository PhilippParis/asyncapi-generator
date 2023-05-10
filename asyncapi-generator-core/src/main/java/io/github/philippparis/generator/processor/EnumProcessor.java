package io.github.philippparis.generator.processor;

import io.github.philippparis.generator.Repository;
import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.types.EnumType;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;

public class EnumProcessor extends Processor {

    private final Repository repository;

    public EnumProcessor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(Schema schema) {
        return !schema.getEnums().isEmpty();
    }

    @Override
    public Type process(AsyncApiGenerator generator, String id, Schema schema) {
        return repository.put(id, new EnumType(schema, id, generator.getOptions()));
    }
}
