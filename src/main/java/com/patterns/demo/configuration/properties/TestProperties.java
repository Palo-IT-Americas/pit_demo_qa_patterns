package com.patterns.demo.configuration.properties;

import lombok.Data;

@Data
public class TestProperties {
    private RestProperties rest;
    // Futuros nodos de configuración, por ejemplo:
    // private DbProperties db;
    // private SecurityProperties security;
} 