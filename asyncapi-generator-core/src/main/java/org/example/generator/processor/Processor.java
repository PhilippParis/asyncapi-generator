package org.example.generator.processor;

import org.example.generator.AsyncApiGenerator;
import org.example.generator.types.Type;
import org.example.parser.model.Schema;

public abstract class Processor {

    public abstract boolean check(final Schema schema);

    public abstract Type process(final AsyncApiGenerator generator, final String id, final Schema schema);
}
