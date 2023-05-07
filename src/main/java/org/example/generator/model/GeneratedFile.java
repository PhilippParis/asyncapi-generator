package org.example.generator.model;

import java.nio.file.Path;

public class GeneratedFile {
    private Path path;
    private String content;

    public GeneratedFile(Path path, String content) {
        this.path = path;
        this.content = content;
    }

    public Path getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }
}
