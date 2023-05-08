package org.example.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.parser.model.AsyncApiDocument;

public class AsyncApiParser {

    private static ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static AsyncApiDocument parse(final String document) throws JsonProcessingException {
        return MAPPER.readValue(document, AsyncApiDocument.class);
    }

}
