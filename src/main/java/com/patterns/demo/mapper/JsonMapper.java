package com.patterns.demo.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * Singleton pattern para el mapeo de JSON
 */
public class JsonMapper {
    private static JsonMapper instance;
    private final ObjectMapper mapper;

    private JsonMapper() {
        this.mapper = new ObjectMapper()
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static JsonMapper getInstance() {
        if (instance == null) {
            synchronized (JsonMapper.class) {
                if (instance == null) {
                    instance = new JsonMapper();
                }
            }
        }
        return instance;
    }

    public <T> T toConcreteClass(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing to class {}: {}", clazz.getSimpleName(), e.getMessage());
            throw new RuntimeException("Error deserializing JSON", e);
        }
    }

    public boolean canDeserialize(String json, Class<?> clazz) {
        try {
            mapper.readValue(json, clazz);
            return true;
        } catch (JsonProcessingException e) {
            log.debug("JSON cannot be deserialized to class {}: {}", clazz.getSimpleName(), e.getMessage());
            return false;
        }
    }

    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object of class {}: {}", object.getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException("Error serializing to JSON", e);
        }
    }
} 