package com.movie.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class CommentDto implements Serializable {
    private @Id Long id;
    private String text;
    @JsonBackReference(value = "user")
    private UserDto user;
    @JsonBackReference(value = "movie")
    private MovieDto movie;
}
