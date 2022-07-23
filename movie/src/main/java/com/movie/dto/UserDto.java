package com.movie.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movie.model.type.MembershipType;
import com.movie.model.type.UserType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class UserDto implements Serializable {
    private Long id;
    @Email
    private @NotBlank String email;
    private String firstName;
    private String lastName;
    private @NotBlank String password;
    private String newPassword;
    private UserType userType;
    private Boolean isEnable;
    private MembershipType membershipType;
    private LocalDate expiringTime;
    private Integer addingMovieRight;
    @JsonManagedReference(value = "user1")
    private List<MovieDto> movies = new ArrayList<>();
    @JsonManagedReference(value = "user")
    private List<CommentDto> comments = new ArrayList<>();
    private PaymentDto payment;


}
