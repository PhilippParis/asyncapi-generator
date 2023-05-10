package io.github.philippparis.generator.processor;

import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;

public abstract class Processor {

    public abstract boolean check(final Schema schema);

    public abstract Type process(final AsyncApiGenerator generator, final String id, final Schema schema);
}
