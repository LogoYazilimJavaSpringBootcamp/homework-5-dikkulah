package com.movie.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class GenreDto implements Serializable {
    private @Id Long id;
    private @NotBlank String name;


}
