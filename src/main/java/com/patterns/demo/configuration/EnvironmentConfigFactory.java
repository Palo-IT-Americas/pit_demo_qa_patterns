package com.patterns.demo.configuration;

import com.patterns.demo.configuration.loader.JsonConfigurationLoader;
import com.patterns.demo.configuration.properties.TestProperties;

public abstract class EnvironmentConfigFactory {
    public abstract TestProperties createProperties();

    public static EnvironmentConfigFactory getFactory(String environment) {
        JsonConfigurationLoader loader = new JsonConfigurationLoader();
        if (environment.equals("dev")) {
            return new DevEnvironmentConfigFactory(loader);
        } else if (environment.equals("prod")) {
            return new ProdEnvironmentConfigFactory(loader);
        }
        throw new IllegalArgumentException("Invalid environment: " + environment);
    }
    
} 