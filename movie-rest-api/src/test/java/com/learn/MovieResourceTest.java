package com.learn;

import com.google.inject.Inject;
import com.learn.dto.response.ExceptionResponse;
import com.learn.entity.Movie;
import com.learn.exception.NotFoundException;
import com.learn.service.MovieService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
public class MovieResourceTest {

    @Inject
    MovieService movieService;

    @BeforeEach
    void setUp() {
        when(movieService.findAll()).thenThrow(new RuntimeException("No connection"));
                //.thenReturn(List.of(
//                new Movie(1L, "Inception", "English", "USA", LocalDate.of(2010, 7, 16)),
//                new Movie(2L, "The Godfather", "French", "USA", LocalDate.of(1972, 3, 24)),
//                new Movie(3L, "The Shawshank Redemption", "English", "USA", LocalDate.of(1994, 10, 14))
//        ));
    }

    @Test
    void shouldReturnSameResults() {
        Movie movie1 = given()
                .when()
                .get("/movies/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Movie.class);

        Movie movie2 = given()
                .when()
                .get("/movies/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Movie.class);

        assertEquals(movie1.getId(), movie2.getId());
    }

    @Test
    void shouldReturnDifferentResults() {
        Movie movie1 = given()
                .when()
                .get("/movies/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Movie.class);

        Movie movie2 = given()
                .when()
                .get("/movies/2")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Movie.class);

        assertNotEquals(movie1, movie2);
    }

    @Test
    void shouldReturnResultBasedOnParam() {
        Response response = given()
                .when()
                .get("/movies/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Movie movie = response.as(Movie.class);

        assertEquals(2L, movie.getId());
    }

    @Test
    void shouldThrowNotFoundException() {
        Response response = given()
                .when()
                .get("/movies/4")
                .then()
                .statusCode(400)
                .extract()
                .response();

        ExceptionResponse exceptionResponse = response.as(ExceptionResponse.class);

        assertEquals(new NotFoundException("Movie", "id", "4").getMessage(), exceptionResponse.getMessage());
    }

    @Test
    void shouldGetValuesFormService() {
        Movie movie = given()
                .when()
                .get("/movies/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Movie.class);


        verify(movieService).findAll();
        assertTrue(
                movieService.findAll()
                        .stream()
                        .map(Movie::getId)
                        .collect(Collectors.toUnmodifiableList())
                        .contains(movie.getId())
        );
    }
}
