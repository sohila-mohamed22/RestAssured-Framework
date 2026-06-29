package com.platziapi.specs;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.containsString;

/**
 * Contains reusable response validations.
 *
 * Responsibilities:
 * - Expected status codes
 * - Content-Type validation
 * - Common response assertions
 *
 * Helps standardize response validation across tests.
 */
public class ResponseSpecs {


    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}
