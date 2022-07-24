package com.movie.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.movie.model.Movie;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
public class GenreDto implements Serializable {
    private @Id Long id;
    private @NotBlank String name;


}
