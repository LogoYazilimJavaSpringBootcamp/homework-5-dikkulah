package com.movie.model;

import com.movie.model.type.MembershipType;
import com.movie.model.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private @Id Long id;
    @Column(unique = true)
    private @Email @NotBlank String email;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String password;
    private Boolean isEnable;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;
    @Enumerated(EnumType.STRING)
    private MembershipType membershipType = MembershipType.FREE;
    private LocalDate expiringTime = LocalDate.now();
    private Integer addingMovieRight;
    @OneToMany(mappedBy = "user")
    private List<Movie> movies = new ArrayList<>();


}
