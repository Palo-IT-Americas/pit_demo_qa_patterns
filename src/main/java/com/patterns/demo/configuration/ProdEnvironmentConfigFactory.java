package com.patterns.demo.configuration;

import com.patterns.demo.configuration.loader.ConfigurationLoader;
import com.patterns.demo.configuration.properties.TestProperties;

/**
 * Factory class for creating properties for the production environment.
 * 
 * This class extends the EnvironmentConfigFactory class and provides a method to create properties for the production environment.
 */
public class ProdEnvironmentConfigFactory extends EnvironmentConfigFactory {
    private final ConfigurationLoader loader;

    public ProdEnvironmentConfigFactory(ConfigurationLoader loader) {
        this.loader = loader;
    }

    @Override
    public TestProperties createProperties() {
        try {
            return loader.getEnvironmentConfig("prod");
        } catch (Exception e) {
            throw new RuntimeException("Error creating properties for dev environment", e);
        }
    }
} 