package com.movie.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDto implements Serializable {
    private final String text;
    private final UserDto user;
    private final MovieDto movie;
}
