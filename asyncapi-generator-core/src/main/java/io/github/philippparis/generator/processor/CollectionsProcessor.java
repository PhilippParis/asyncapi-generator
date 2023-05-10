package io.github.philippparis.generator.processor;

import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.types.Collections;
import io.github.philippparis.generator.types.Type;
import io.github.philippparis.parser.model.Schema;
import io.github.philippparis.util.Utils;

public class CollectionsProcessor extends Processor {

    @Override
    public boolean check(Schema schema) {
        return "array".equals(schema.getType()) || schema.getAdditionalProperties() != null;
    }

    @Override
    public Type process(final AsyncApiGenerator generator, String id, Schema schema) {
        if ("array".equals(schema.getType())) {
            return new Collections.ListType(schema, generator.processSchema(Utils.getTypeId(schema.getItems()), schema.getItems()));
        }
        if (schema.getAdditionalProperties() != null) {
            return new Collections.MapType(schema, generator.processSchema(Utils.getTypeId(schema.getAdditionalProperties()), schema.getAdditionalProperties()));
        }
        throw new IllegalStateException("failed to process CollectionType: " + schema);
    }
}
