package io.github.philippparis.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CaseUtils {

    public static String toCamelCase(final String value) {
        final var pascalCase = toPascalCase(value);
        return Character.toLowerCase(pascalCase.charAt(0)) + pascalCase.substring(1);
    }

    public static String toPascalCase(final String value) {
        return Stream.of(value.split("[\\W_]+"))
                .map(i -> Character.toUpperCase(i.charAt(0)) + i.substring(1))
                .collect(Collectors.joining());
    }

    public static String toSnakeCase(final String value) {
        return Stream.of(value.split("[\\W_]+"))
                .map(String::toLowerCase)
                .collect(Collectors.joining("_"));
    }

}
