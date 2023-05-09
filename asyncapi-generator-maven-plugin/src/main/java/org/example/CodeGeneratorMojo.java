package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.example.generator.AsyncApiGenerator;
import org.example.generator.model.Options;
import org.example.parser.AsyncApiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class CodeGeneratorMojo extends AbstractMojo {

    private final Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorMojo.class);

    @Parameter(name = "inputSpec", required = true)
    private String inputSpec;
    @Parameter(name = "modelPackage", defaultValue = "org.asyncapi.models")
    private String modelPackage;
    @Parameter(name = "modelNameSuffix", defaultValue = "")
    private String modelNameSuffix;
    @Parameter(name = "outputDir", defaultValue = "generated-sources/asyncapi")
    private String outputDir;
    @Parameter(name = "javaDir", defaultValue = "src/gen/java/main")
    private String javaDir;
    @Parameter(readonly = true, required = true, defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            final var options = new Options();
            options.setModelPackage(modelPackage);
            options.setModelNameSuffix(modelNameSuffix);
            options.setOutputDir(outputDir);
            options.setJavaDir(javaDir);

            final var spec = FileUtils.readFileToString(new File(inputSpec), StandardCharsets.UTF_8);
            final var generator = new AsyncApiGenerator(AsyncApiParser.parse(spec), options);
            final var generatedFiles = generator.exec();

            final var targetDir = new File(project.getBuild().getDirectory());
            final var asyncapiDir = new File(targetDir, outputDir);
            project.addCompileSourceRoot(Path.of(asyncapiDir.getPath(), javaDir).toString());

            if (asyncapiDir.exists() && asyncapiDir.isDirectory()) {
                FileUtils.cleanDirectory(asyncapiDir);
            }
            for (final var generatedFile : generatedFiles) {
                final var file = new File(targetDir, generatedFile.getPath().toString());
                LOGGER.info("writing file " + file.getAbsolutePath());
                FileUtils.writeStringToFile(file, generatedFile.getContent(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new MojoExecutionException("code generation failed", e);
        }
    }

}
