package io.github.philippparis.generator;

public class AnonymousTypeName {

    private static int COUNTER = 1;

    public static String next() {
        return "AnonymousClass" + COUNTER++;
    }

}
