package com.patterns.demo.configuration;

import com.patterns.demo.configuration.loader.ConfigurationLoader;
import com.patterns.demo.configuration.properties.TestProperties;

/**
 * Abstract Factory pattern for creating properties for the development environment.
 * 
 * This class extends the EnvironmentConfigFactory class and provides a method to create properties for the development environment.
 * 
 */
public class DevEnvironmentConfigFactory extends EnvironmentConfigFactory {
    private final ConfigurationLoader loader;
  
    public DevEnvironmentConfigFactory(ConfigurationLoader loader) {
        this.loader = loader;
    }

    @Override
    public TestProperties createProperties() {
        try {
            return loader.getEnvironmentConfig("dev");
        } catch (Exception e) {
            throw new RuntimeException("Error creating properties for dev environment", e);
        }
    }
} 