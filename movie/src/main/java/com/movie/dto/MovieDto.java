package com.movie.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class MovieDto implements Serializable {
    private @Id Long id;
    private String title;
    private Integer year;
    @JsonManagedReference(value = "movie")
    private List<CommentDto> comments = new ArrayList<>();
    @JsonManagedReference(value = "genre")
    private Set<GenreDto> genres = new HashSet<>();
    @JsonManagedReference(value = "person")
    private Set<PersonDto> directors = new HashSet<>();
    @JsonManagedReference(value = "person")
    private Set<PersonDto> writers = new HashSet<>();
    @JsonManagedReference(value = "person")
    private Set<PersonDto> actors = new HashSet<>();
    @JsonBackReference(value = "user1")
    private UserDto user;


}
