package com.learn.service;

import com.learn.entity.Movie;
import com.learn.exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MovieService {

    private final List<Movie> movies;

    public MovieService() {
        movies = new ArrayList<>();
        movies.add(new Movie(1L, "Inception", "English", "USA", LocalDate.of(2010, 7, 16)));
        movies.add(new Movie(2L, "The Godfather", "French", "USA", LocalDate.of(1972, 3, 24)));
        movies.add(new Movie(3L, "The Shawshank Redemption", "English", "USA", LocalDate.of(1994, 10, 14)));
        movies.add(new Movie(4L, "Pulp Fiction", "English", "USA", LocalDate.of(1994, 10, 14)));
        movies.add(new Movie(5L, "The Dark Knight", "Romanian", "USA", LocalDate.of(2008, 7, 18)));
        movies.add(new Movie(6L, "The Lord of the Rings: The Return of the King", "English", "USA", LocalDate.of(2003, 12, 17)));
        movies.add(new Movie(7L, "Forrest Gump", "English", "USA", LocalDate.of(1994, 7, 6)));
        movies.add(new Movie(8L, "Fight Club", "Finish", "USA", LocalDate.of(1999, 10, 15)));
        movies.add(new Movie(9L, "The Matrix", "Spanish", "USA", LocalDate.of(1999, 3, 31)));
        movies.add(new Movie(10L, "Gladiator", "English", "USA", LocalDate.of(2000, 5, 5)));
    }

    public List<Movie> findAll() {
        return movies;
    }
}
