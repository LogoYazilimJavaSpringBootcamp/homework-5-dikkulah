package com.movie.dto;

import com.movie.model.type.MembershipType;
import com.movie.model.type.UserType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.List;


@Data
public class UserDto implements Serializable {
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String password;
    private final UserType userType;
    private final MembershipType membershipType;
    private final LocalDate expiringTime;
    private final Boolean enabled;
    private final Boolean locked;
    private final List<MovieDto> movies;
    private final List<CommentDto> comments;
}
