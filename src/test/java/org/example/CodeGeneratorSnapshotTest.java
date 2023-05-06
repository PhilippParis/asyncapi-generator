package org.example;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeGeneratorSnapshotTest {

    private static final String DELIMITER = "\r\n=============\r\n";
    public static final String SPECS_DIR = "/specs";
    public static final String SNAPSHOTS_DIR = "/snapshots";

    @ParameterizedTest
    @MethodSource("provideTestInputs")
    void generatedCodeShouldMatchSnapshots(final Path pathToSpec, final Path pathToSnapshot) throws IOException {
        final var spec = IOUtils.toString(pathToSpec.toUri(), StandardCharsets.UTF_8);
        final var snapshot = IOUtils.toString(pathToSnapshot.toUri(), StandardCharsets.UTF_8);

        final var generator = new AsyncApiGenerator(AsyncApiParser.parse(spec), new Options());
        generator.exec();

        final var models = generator.generateModels();
        assertEquals(snapshot, String.join(DELIMITER, models));
    }

    private static Stream<Arguments> provideTestInputs() throws IOException, URISyntaxException {
        final var specsDir = Paths.get(CodeGeneratorSnapshotTest.class.getResource(SPECS_DIR).toURI());
        final var snapshotsDir = Paths.get(CodeGeneratorSnapshotTest.class.getResource(SNAPSHOTS_DIR).toURI());
        return Files.list(specsDir)
                .map(i -> FilenameUtils.removeExtension(i.getFileName().toString()))
                .map(i -> Arguments.of(
                        Paths.get(specsDir.toString(), i + ".yml"),
                        Paths.get(snapshotsDir.toString(), i + ".txt")));
    }

}