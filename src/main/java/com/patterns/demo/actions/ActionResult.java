package com.patterns.demo.actions;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

import com.patterns.demo.mapper.JsonMapper;

@Data
@Builder
public class ActionResult {
    private final int statusCode;
    private final String rawBody;
    private final Map<String, String> headers;
    
    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }

    public boolean hasStatusCode(int code) {
        return statusCode == code;
    }

    public <T> T as(Class<T> clazz) {
        return JsonMapper.getInstance().toConcreteClass(rawBody, clazz);
    }

    public boolean canBeDeserialized(Class<?> clazz) {
        return JsonMapper.getInstance().canDeserialize(rawBody, clazz);
    }
} 