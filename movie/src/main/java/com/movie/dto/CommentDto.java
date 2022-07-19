package com.movie.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {
    private String text;
    private UserDto user;
    private MovieDto movie;
}
