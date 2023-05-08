package org.example.util;

import org.example.generator.model.Options;
import org.example.generator.types.Type;

import java.nio.file.Path;

public final class PathUtils {

    public static Path getModelPath(final Options options, final Type type) {
        final var javaSrcDir = Path.of(options.getOutputDir(), options.getJavaDir());
        final var modelsDir = Path.of(javaSrcDir.toString(), packageToFilePath(options.getModelPackage()).toString());
        return Path.of(modelsDir.toString(), type.getTypeName() + ".java");
    }

    public static Path packageToFilePath(String packagePath) {
        return Path.of("", packagePath.split("\\."));
    }

}
