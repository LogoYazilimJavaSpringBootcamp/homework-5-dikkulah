package com.movie.dto;

import com.movie.model.type.PersonType;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PersonDto implements Serializable {
    private String firstName;
    private String lastName;
    private Set<MovieDto> movies = new HashSet<>();
    private List<PersonType> jobs = new ArrayList<>();
}
