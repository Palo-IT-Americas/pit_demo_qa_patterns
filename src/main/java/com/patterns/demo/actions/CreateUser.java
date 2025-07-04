package com.patterns.demo.actions;

import com.patterns.demo.actors.Actor;
import com.patterns.demo.configuration.properties.TestProperties;
import com.patterns.demo.request.UserRequest;

/**
 * Command pattern para crear un usuario
 */
public class CreateUser extends AbstractRestAction implements Action{
    private final UserRequest user;
    private final Actor actor;

    private CreateUser(Builder builder) {
        this.user = builder.user;
        this.actor = builder.actor;
    }

    @Override
    public ActionResult doInContext(TestProperties config) {
        try {
            String url = buildUrl(config, config.getRest().getPaths().getUser());
            return executePost(url, user, config);
        } catch (Exception e) {
            throw new RuntimeException("Error executing CreateUser action", e);
        }
    }

    public static class Builder {
        private UserRequest user;
        private Actor actor;

        public Builder withUser(UserRequest user) {
            this.user = user;
            return this;
        }

        public Builder as(Actor actor) {
            this.actor = actor;
            return this;
        }

        public CreateUser build() {
            if (user == null) throw new IllegalArgumentException("User is required");
            if (actor == null) throw new IllegalArgumentException("Actor is required");
            return new CreateUser(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
} 