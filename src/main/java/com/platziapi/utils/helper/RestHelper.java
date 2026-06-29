package com.platziapi.utils.helper;

import com.platziapi.utils.manager.LogsManager;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * Wrapper around REST Assured HTTP methods.
 *
 * Responsibilities:
 * - Send GET requests
 * - Send POST requests
 * - Send PUT requests
 * - Send DELETE requests
 *
 * Provides generic methods for interacting with APIs
 * while hiding REST Assured implementation details.
 */
public class RestHelper {

    public static <T> T restGet(RequestSpecification requestSpec, String endpoint, ResponseSpecification responseSpec, Class<T> responseClass){
        return
                given()
                        .spec(requestSpec)
                .when()
                        .get(endpoint)
                .then()
                        .spec(responseSpec)
                        .log()
                        .all()
                        .extract()
                        .as(responseClass);
    }

    public static <T> T restGet(RequestSpecification requestSpec, String endpoint, ResponseSpecification responseSpec, TypeRef<T> typeRef){
        return
                given()
                        .spec(requestSpec)
                .when()
                        .get(endpoint)
                .then()
                        .spec(responseSpec)
                        .log()
                        .all()
                        .extract()
                        .as(typeRef);
    }


    public static <T> T restPost(RequestSpecification requestSpec, String endpoint, Object body, ResponseSpecification responseSpec,  Class<T>responseClass){
        return
                given()
                        .spec(requestSpec)
                        .body(body)
                .when()
                        .post(endpoint)
                .then()
                        .spec(responseSpec)
                        .log()
                        .all()
                        .extract()
                        .as(responseClass);
    }

    public static <T> T restPut(RequestSpecification requestSpec, String endpoint, Object body, ResponseSpecification responseSpec,  Class<T>responseClass){
        return
                given()
                        .spec(requestSpec)
                        .body(body)
                .when()
                        .put(endpoint)
                .then()
                        .spec(responseSpec)
                        .extract()
                        .as(responseClass);
    }

    public static String restDelete(RequestSpecification requestSpec, String endpoint, ResponseSpecification responseSpec){
        return
                given()
                        .spec(requestSpec)
                .when()
                        .delete(endpoint)
                .then()
                        .spec(responseSpec)
                        .log()
                        .all()
                        .extract()
                        .asString();
    }

    public static <T> T restDelete(RequestSpecification requestSpec, String endpoint, ResponseSpecification responseSpec, Class<T> responseClass){
        return
                given()
                        .spec(requestSpec)
                        .when()
                        .delete(endpoint)
                        .then()
                        .spec(responseSpec)
                        .log()
                        .all()
                        .extract()
                        .as(responseClass);
    }


}
