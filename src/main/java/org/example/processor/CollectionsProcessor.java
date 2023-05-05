package org.example.processor;

import org.example.model.Schema;
import org.example.AsyncApiGenerator;
import org.example.types.Collections;
import org.example.types.Type;
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
