package com.learn.controller;

import com.learn.entity.Movie;
import com.learn.exception.NotFoundException;
import com.learn.service.MovieService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(value = "/movies")
public class MovieResource {

    @Inject
    MovieService movieService;

    @GET
    @Path(value = "/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Movie findById(@PathParam("id") Long id) {
        return movieService.findAll()
                .stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Movie", "id", id.toString()));
    }
}
