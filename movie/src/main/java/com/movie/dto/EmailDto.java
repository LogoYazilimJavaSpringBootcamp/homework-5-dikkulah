package com.movie.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class EmailDto implements Serializable {
    private @Id Long id;
    @NotEmpty(message = "email cannot be empty")
    private @Email String from;
    @NotEmpty(message = "email cannot be empty")
    private @Email String to;
    private String title;
    private String description;

    private String sendingTime;
}
