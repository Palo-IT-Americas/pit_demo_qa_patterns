package com.patterns.demo.configuration.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.patterns.demo.configuration.properties.TestProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.InputStream;

/**
 * Strategy pattern implementation for loading configuration from a JSON file.
 * 
 * This class implements the ConfigurationLoader interface and provides a method to load configuration from a JSON file.
 * 
 */
@Data
public class JsonConfigurationLoader implements ConfigurationLoader {
    private static final String CONFIG_FILE = "/config.json";
    private JsonNode configuration;
    private ObjectMapper mapper;

    public JsonConfigurationLoader() {
        try (InputStream is = getClass().getResourceAsStream(CONFIG_FILE)) {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            configuration = mapper.readTree(is);
        } catch (Exception e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    @SneakyThrows    
    public TestProperties getEnvironmentConfig(String environment){
        return mapper.treeToValue(configuration.get(environment), TestProperties.class);
    }
} 