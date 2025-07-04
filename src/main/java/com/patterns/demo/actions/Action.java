package com.patterns.demo.actions;

import com.patterns.demo.configuration.properties.TestProperties;

public interface Action {
    ActionResult doInContext(TestProperties config);
} 