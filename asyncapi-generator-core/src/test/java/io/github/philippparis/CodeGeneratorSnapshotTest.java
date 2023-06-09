package io.github.philippparis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import io.github.philippparis.generator.AsyncApiGenerator;
import io.github.philippparis.generator.model.GeneratedFile;
import io.github.philippparis.generator.model.Options;
import io.github.philippparis.parser.AsyncApiParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CodeGeneratorSnapshotTest {

    public static final String OPTIONS_DIR = "/options";
    public static final String SPECS_DIR = "/specs";
    public static final String SNAPSHOTS_DIR = "/snapshots";

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideTestInputs")
    void generatedCodeShouldMatchSnapshots(final String specName) throws IOException, URISyntaxException {
        final var specsDir = Paths.get(getClass().getResource(SPECS_DIR).toURI());
        final var snapshotsDir = Paths.get(getClass().getResource(SNAPSHOTS_DIR).toURI());

        final var spec = IOUtils.toString(Path.of(specsDir.toString(), specName + ".yml").toUri(), StandardCharsets.UTF_8);
        final var snapshot = IOUtils.toString(Path.of(snapshotsDir.toString(), specName + ".txt").toUri(), StandardCharsets.UTF_8);

        final var generator = new AsyncApiGenerator(AsyncApiParser.parse(spec), getOptions(specName));
        final var files = generator.exec() .stream()
                                            .sorted(Comparator.comparing(GeneratedFile::getPath))
                                            .collect(Collectors.toList());

        assertFalse(files.isEmpty());
        assertEquals(snapshot.replace(StringUtils.CR, ""), createSnapshot(files).replace(StringUtils.CR, ""));
    }

    private static Stream<Arguments> provideTestInputs() throws IOException, URISyntaxException {
        final var specsDir = Paths.get(CodeGeneratorSnapshotTest.class.getResource(SPECS_DIR).toURI());
        return Files.list(specsDir)
                .map(i -> FilenameUtils.removeExtension(i.getFileName().toString()))
                .map(Arguments::of);
    }

    private String createSnapshot(List<GeneratedFile> files) {
        final var sb = new StringBuilder();
        for (final var file : files) {
            sb.append(file.getPath().toString().replace("/", "\\")).append("\r\n");
            sb.append("--------------------\r\n");
            sb.append(file.getContent()).append("\r\n");
            sb.append("====================\r\n");
        }
        return sb.toString();
    }

    private Options getOptions(final String specName) throws URISyntaxException, IOException {
        final var optionsDir = Paths.get(getClass().getResource(OPTIONS_DIR).toURI());
        final var optionsFile = new File(optionsDir.toString(), specName + ".yml");
        if (!optionsFile.exists()) {
            return new Options();
        }
        return new ObjectMapper(new YAMLFactory()).readValue(optionsFile, Options.class);
    }

}