package com.learn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Movie {

    private Long id;
    private String title;
    private String language;
    private String country;
    private LocalDate releaseDate;
}
