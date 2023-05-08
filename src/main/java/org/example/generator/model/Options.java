package org.example.generator.model;

public class Options {

    private String modelPackage = "com.asyncapi.models";
    private String modelNameSuffix = "";
    private String outputDir = "generated-sources/asyncapi";
    private String javaDir = "src/gen/java/main";

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getJavaDir() {
        return javaDir;
    }

    public void setJavaDir(String javaDir) {
        this.javaDir = javaDir;
    }

    public String getModelNameSuffix() {
        return modelNameSuffix;
    }

    public void setModelNameSuffix(String modelNameSuffix) {
        this.modelNameSuffix = modelNameSuffix;
    }
}
