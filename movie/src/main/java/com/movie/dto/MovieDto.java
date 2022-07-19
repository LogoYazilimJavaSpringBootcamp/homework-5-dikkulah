package com.movie.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class MovieDto implements Serializable {
    private String title;
    private Integer year;
    private Set<GenreDto> genres = new HashSet<>();
    private Set<PersonDto> directors = new HashSet<>();
    private Set<PersonDto> writers = new HashSet<>();
    private Set<PersonDto> actors = new HashSet<>();
    @JsonBackReference
    private UserDto user;


}
