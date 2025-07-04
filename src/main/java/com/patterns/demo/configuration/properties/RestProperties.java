package com.patterns.demo.configuration.properties;

import lombok.Data;

@Data
public class RestProperties {
    private String baseUrl;
    private Paths paths;
    private Credentials credentials;

    @Data
    public static class Paths {
        private String user;
    }

    @Data
    public static class Credentials {
        private String user;
        private String password;
    }
} 