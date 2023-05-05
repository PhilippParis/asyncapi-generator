package org.example;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ModelGeneratorTest {


    @Test
    void parse() throws IOException {
        final var doc = AsyncApiParser.parse(IOUtils.resourceToString("/inputs/enum.yml", StandardCharsets.UTF_8));
        final var options = new Options();
        options.setModelPackage("com.asyncapi.models");

        AsyncApiGenerator generator = new AsyncApiGenerator(doc, options);
        generator.exec();

        final var models = generator.generateModels();

        assertEquals(IOUtils.resourceToString("/outputs/enum.txt", StandardCharsets.UTF_8),
                models.stream().collect(Collectors.joining("\r\n=============\r\n")));
    }
}