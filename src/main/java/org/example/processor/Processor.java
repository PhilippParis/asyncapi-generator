package org.example.processor;

import org.example.model.Schema;
import org.example.AsyncApiGenerator;
import org.example.types.Type;

public abstract class Processor {

    public abstract boolean check(final Schema schema);

    public abstract Type process(final AsyncApiGenerator generator, final String id, final Schema schema);
}
