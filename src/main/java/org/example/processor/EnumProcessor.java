package org.example.processor;

import org.example.model.Schema;
import org.example.types.EnumType;
import org.example.AsyncApiGenerator;
import org.example.Repository;
import org.example.types.Type;

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
