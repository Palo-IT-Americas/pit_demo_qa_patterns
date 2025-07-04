package com.patterns.demo.configuration.loader;

import com.patterns.demo.configuration.properties.TestProperties;

public interface ConfigurationLoader {
    TestProperties getEnvironmentConfig(String environment);
}
