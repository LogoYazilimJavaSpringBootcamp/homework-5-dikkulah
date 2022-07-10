package com.movie.model.person;

import com.movie.model.Movie;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@MappedSuperclass
public abstract class Person  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    private String fistName;
    @NotBlank
    private String lastName;
    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies;


}