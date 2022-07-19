package com.movie.model;

import com.movie.model.type.PersonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<PersonType> jobs;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    @ManyToMany(mappedBy = "directors")
    private Set<Movie> asDirector;
    @ManyToMany(mappedBy = "actors")
    private Set<Movie> asActor;
    @ManyToMany(mappedBy = "writers")
    private Set<Movie> asWriter;

}