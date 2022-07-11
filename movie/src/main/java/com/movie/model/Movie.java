package com.movie.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private int year;

    @ManyToMany
    @JoinTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "movie_directors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Person> directors = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "movie_writers", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "writer_id"))
    private Set<Person> writers = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Person> actors = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;


}
