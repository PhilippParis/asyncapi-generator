package org.example.util;

import org.example.parser.model.Schema;
import org.example.generator.AnonymousTypeName;

public final class Utils {

    public static String getTypeId(Schema schema) {
        return schema.getRef() == null ? AnonymousTypeName.next() : Utils.refToTypeId(schema.getRef());
    }

    public static String refToTypeId(String ref) {
        final var split = ref.split("/");
        return split[split.length - 1];
    }

}
