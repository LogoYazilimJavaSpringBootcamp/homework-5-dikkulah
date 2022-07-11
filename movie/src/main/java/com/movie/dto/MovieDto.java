package com.movie.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
public class MovieDto implements Serializable {
    @NotBlank
    private final String title;
    @NotBlank
    private final int year;
    private final Set<GenreDto> genres;
    private final Set<PersonDto> directors;
    private final Set<PersonDto> writers;
    private final Set<PersonDto> actors;
    private final UserDto user;


}
