package com.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document(collection = "email")
@Getter
@Setter
public class EmailDto {
    private @Email String from;
    private @Email String to;
    private String title;
    private String description;
    private String sendingTime;
}
