package org.example.model;

import java.util.stream.Stream;

public enum Test {
    ONE,
    TWO,
    ;
    private String value;

    public static Test from(String value) {
        return Stream.of(values()).filter(i -> i.value.equals(value)).findAny().orElseThrow(
                () -> new IllegalStateException("Unexpected value '" + value + "'"));
    }

    public static Test from2(String value) {
        for (final var i : values()) {
            if (i.value.equals(value)) {
                return i;
            }
        }
        throw new IllegalStateException("Unexpected value '" + value + "'");
    }
}
