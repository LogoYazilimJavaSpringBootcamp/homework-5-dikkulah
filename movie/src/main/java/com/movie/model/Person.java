package com.movie.model;

import com.movie.model.type.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ElementCollection
    private List<PersonType> jobs;
    @NotBlank
    private String fistName;
    @NotBlank
    private String lastName;
    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies = new HashSet<>();


}