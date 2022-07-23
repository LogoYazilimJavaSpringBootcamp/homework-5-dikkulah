package com.movie.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movie.model.type.PersonType;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PersonDto implements Serializable {
    private @Id Long id;
    private String firstName;
    private String lastName;
    @JsonBackReference(value = "person")
    private Set<MovieDto> movies = new HashSet<>();
    private List<PersonType> jobs = new ArrayList<>();
}
