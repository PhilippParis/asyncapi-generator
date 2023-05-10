# asyncapi-generator
a Java implementation of an asyncapi code generator

## Usage
Use the `asyncapi-generator-maven-plugin`

```xml
<plugin>
    <groupId>io.github.philippparis</groupId>
    <artifactId>asyncapi-generator-maven-plugin</artifactId>
    <version>${asyncapi.generator.version}</version>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${basedir}/src/main/resources/asyncapi.yaml</inputSpec>
                <modelPackage>org.example.asyncapi.models</modelPackage>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## Configuration parameters

| option          | default                    | description                                     |
|-----------------|----------------------------|-------------------------------------------------|
| inputSpec       | [required]                 | path to asyncapi.yaml                           |
| modelPackage    | org.asyncapi.models        | model package                                   |
| modelNameSuffix |                            | suffix for generated model classes (e.g. 'Dto') |
| outputDir       | generated-sources/asyncapi | root directory for all generated files          |
| javaDir         | src/gen/java/main          | root directory for generated java files         |
