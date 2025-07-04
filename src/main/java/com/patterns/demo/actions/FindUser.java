package com.patterns.demo.actions;

import com.patterns.demo.actors.Actor;
import com.patterns.demo.configuration.properties.TestProperties;

/**
 * Command pattern para buscar un usuario por ID
 */
public class FindUser extends AbstractRestAction implements Action {
    private final Long id;
    private final Actor actor;

    private FindUser(Builder builder) {
        this.id = builder.id;
        this.actor = builder.actor;
    }

    @Override
    public ActionResult doInContext(TestProperties config) {
        try {
            String url = buildUrl(config, config.getRest().getPaths().getUser() + "/" + id);
            return executeGet(url, config);
        } catch (Exception e) {
            throw new RuntimeException("Error executing FindUser action", e);
        }
    }

    public static class Builder {
        private Long id;
        private Actor actor;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder as(Actor actor) {
            this.actor = actor;
            return this;
        }

        public FindUser build() {
            if (id == null) throw new IllegalArgumentException("Id is required");
            if (actor == null) throw new IllegalArgumentException("Actor is required");
            return new FindUser(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
} 