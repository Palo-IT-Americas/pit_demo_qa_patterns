package com.patterns.demo.pattern;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.patterns.demo.actions.ActionResult;
import com.patterns.demo.actions.CreateUser;
import com.patterns.demo.actors.Actor;
import com.patterns.demo.configuration.Environment;
import com.patterns.demo.configuration.EnvironmentConfigFactory;
import com.patterns.demo.configuration.properties.TestProperties;
import com.patterns.demo.request.UserRequest;
import com.patterns.demo.testData.UserFactory;

public class PatternUserTest {
    
    private TestProperties config;

    @BeforeEach
    void setup() {
        System.out.println("Environment: " + Environment.getCurrentEnvironment());
        EnvironmentConfigFactory factory = EnvironmentConfigFactory.getFactory(Environment.getCurrentEnvironment());
        config = factory.createProperties();
        System.out.println("Env Config:  " + config.toString());
    }

    @Test
    void shouldCreateUser() {

        UserRequest validUser = UserFactory.createValidUser();

        CreateUser createUser = CreateUser.builder()
            .withUser(validUser)
            .as(new Actor("Mike"))
            .build();

        // Act
        ActionResult result = createUser.doInContext(config);

        // Assert
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.hasStatusCode(201)).isTrue();
        assertThat(result.getRawBody()).isNotEmpty();

        //assertThat(result.canBeDeserialized(UserRequest.class)).isTrue();
        // Verify response contains user data
        UserRequest createdUser = result.as(UserRequest.class);
        assertThat(createdUser.getName()).isEqualTo(validUser.getName());
        assertThat(createdUser.getUsername()).isEqualTo(validUser.getUsername());
        assertThat(createdUser.getEmail()).isEqualTo(validUser.getEmail());
        assertThat(createdUser.getId()).isNotNull();
    }
}
