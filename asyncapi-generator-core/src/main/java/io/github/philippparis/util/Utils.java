package io.github.philippparis.util;

import io.github.philippparis.generator.AnonymousTypeName;
import io.github.philippparis.parser.model.Schema;

import java.nio.file.Path;

public final class Utils {

    public static String getTypeId(Schema schema) {
        return schema.getRef() == null ? AnonymousTypeName.next() : Utils.refToTypeId(schema.getRef());
    }

    public static String refToTypeId(String ref) {
        final var split = ref.split("/");
        return split[split.length - 1];
    }

    public static Path packageToFilePath(String packagePath) {
        return Path.of("", packagePath.split("\\."));
    }

}
