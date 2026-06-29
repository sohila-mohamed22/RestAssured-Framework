package com.platziapi.specs;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import com.platziapi.utils.helper.Constants;

import java.util.HashMap;

/**
 * Contains reusable REST Assured request configurations.
 *
 * Responsibilities:
 * - Base URI
 * - Content-Type
 * - Authentication headers
 * - Common headers
 * - Logging configuration
 *
 * Used by all API requests to avoid duplication.
 */

public class RequestSpecs {

    @Step("Creating request spec")
    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("Creating request spec with query parameters {parameters}")
    public static RequestSpecification requestSpec(HashMap<String,String> parameters){
        return new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URI)
                .setContentType(ContentType.JSON)
                .addQueryParams(parameters)
                .build();
    }

    public static RequestSpecification requestSpec(String accessToken){
        return new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
    }
}
