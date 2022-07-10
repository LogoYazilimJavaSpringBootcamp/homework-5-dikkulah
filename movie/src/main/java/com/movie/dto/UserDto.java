package com.movie.dto;

import com.movie.model.type.MembershipType;
import com.movie.model.type.UserType;
import lombok.*;

@Data
public class UserDto {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;
    private  MembershipType membershipType;
    private  UserType userType;
    private  Boolean locked;
    private  Boolean enabled;

    public UserDto() {
    }
}
