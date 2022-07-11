package com.movie.dto;

import com.movie.model.type.PersonType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class PersonDto implements Serializable {
    @NotBlank
    private final String fistName;
    @NotBlank
    private final String lastName;
    private final Set<MovieDto> movies;
    private final List<PersonType> jobs;
}
