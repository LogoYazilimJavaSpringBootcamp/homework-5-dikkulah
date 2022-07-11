package com.movie.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class GenreDto implements Serializable {
    @NotBlank
    private final String name;
}
