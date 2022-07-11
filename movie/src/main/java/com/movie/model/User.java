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
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User /*implements UserDetails*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType = MembershipType.FREE;
    private LocalDate expiringTime = LocalDate.now();
    private Boolean enabled = true;
    private Boolean locked = false;

    @OneToMany(mappedBy = "user")
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

 /*   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userType.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }*/


}
