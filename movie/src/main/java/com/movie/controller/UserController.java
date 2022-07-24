package com.movie.controller;

import com.movie.dto.CommentDto;
import com.movie.dto.MovieDto;
import com.movie.dto.UserDto;
import com.movie.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor@Log4j2
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("registerAll")
    public ResponseEntity<List<UserDto>> registerAll(@RequestBody List<UserDto> request){
        return ResponseEntity.ok().body(userService.registerAll(request));
    }

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto request){
        return ResponseEntity.ok().body(userService.register(request));
    }
    @PutMapping("update")
    public ResponseEntity<UserDto> updateNameAndLastName(@RequestBody UserDto request){
        return ResponseEntity.ok().body(userService.updateFullNameAndPassword(request));
    }
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDto request)  {
        return ResponseEntity.ok().body(userService.login(request));
    }
    @GetMapping("comments/{userId}")
    public ResponseEntity<List<CommentDto>> getCommentsOfUser(@PathVariable Long userId){
        return ResponseEntity.ok().body(userService.getCommentsOfUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email){

        return ResponseEntity.ok().body(userService.getUser(email));
    }
    @GetMapping("/{email}/{password}")
    public ResponseEntity<List<MovieDto>> getMyMovies(@PathVariable String email, @PathVariable String password){

        return ResponseEntity.ok().body(userService.getMyMovies(email,password));
    }

}
