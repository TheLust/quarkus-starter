package com.learn;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
class ExampleResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    void shouldReturnValueForParam() {
        int id1 = 1;
        int id2 = 2;

        // Perform GET requests for both IDs
        Response response1 = RestAssured.given()
                .when().get("/movies/{id}", id1)
                .then().statusCode(200)
                .extract().response();

        Response response2 = RestAssured.given()
                .when().get("/movies/{id}", id2)
                .then().statusCode(200)
                .extract().response();

        // different
        String responseBody1 = response1.getBody().asString();
        String responseBody2 = response2.getBody().asString();

        assert !responseBody1.equals(responseBody2) : "Responses for different IDs are not different";
    }

}