package org.example.generator.processor;

import org.example.parser.model.Schema;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.types.Collections;
import org.example.generator.types.Type;
import org.example.util.Utils;

public class CollectionsProcessor extends Processor {

    @Override
    public boolean check(Schema schema) {
        return "array".equals(schema.getType()) || schema.getAdditionalProperties() != null;
    }

    @Override
    public Type process(final AsyncApiGenerator generator, String id, Schema schema) {
        if ("array".equals(schema.getType())) {
            return new Collections.ListType(schema, generator.process(Utils.getTypeId(schema.getItems()), schema.getItems()));
        }
        if (schema.getAdditionalProperties() != null) {
            return new Collections.MapType(schema, generator.process(Utils.getTypeId(schema.getAdditionalProperties()), schema.getAdditionalProperties()));
        }
        throw new IllegalStateException("failed to process CollectionType: " + schema);
    }
}
