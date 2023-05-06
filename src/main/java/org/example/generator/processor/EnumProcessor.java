package org.example.generator.processor;

import org.example.parser.model.Schema;
import org.example.generator.types.EnumType;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.Repository;
import org.example.generator.types.Type;

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
        return repository.put(id, new EnumType(schema, id));
    }
}
