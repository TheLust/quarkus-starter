package com.learn.advice;

import com.learn.dto.response.ExceptionResponse;
import com.learn.exception.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionResponse(e.getMessage()))
                .build();
    }
}
