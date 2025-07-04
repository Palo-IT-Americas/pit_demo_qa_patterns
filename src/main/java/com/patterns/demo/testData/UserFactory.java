package com.patterns.demo.testData;

import com.github.javafaker.Faker;
import com.patterns.demo.request.UserRequest;

/**
 * Factory pattern para crear usuarios
 */
public class UserFactory {
    private static final Faker faker = new Faker();

    public static UserRequest createValidUser() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .website(faker.internet().url())
                .address(createValidAddress())
                .company(createValidCompany())
                .build();
    }

    public static UserRequest createUserWithNoPhone() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .website(faker.internet().url())
                .address(createValidAddress())
                .company(createValidCompany())
                .build();
    }

    public static UserRequest createUserWithNoUsername() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .website(faker.internet().url())
                .address(createValidAddress())
                .company(createValidCompany())
                .build();
    }

    public static UserRequest createUserWithNoCompany() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .website(faker.internet().url())
                .address(createValidAddress())
                .build();
    }

    private static UserRequest.Address createValidAddress() {
        return UserRequest.Address.builder()
                .street(faker.address().streetAddress())
                .suite(faker.address().secondaryAddress())
                .city(faker.address().city())
                .zipcode(faker.address().zipCode())
                .geo(createValidGeo())
                .build();
    }

    private static UserRequest.Geo createValidGeo() {
        return UserRequest.Geo.builder()
                .lat(faker.address().latitude())
                .lng(faker.address().longitude())
                .build();
    }

    private static UserRequest.Company createValidCompany() {
        return UserRequest.Company.builder()
                .name(faker.company().name())
                .catchPhrase(faker.company().catchPhrase())
                .bs(faker.company().bs())
                .build();
    }
} 