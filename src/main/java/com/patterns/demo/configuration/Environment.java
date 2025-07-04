package com.patterns.demo.configuration;

public class Environment {
    private static final String DEFAULT_ENV = "dev";
    private static final String ENV_PROPERTY = "env";

    public static String getCurrentEnvironment() {
        return System.getProperty(ENV_PROPERTY, DEFAULT_ENV);
    }
} 