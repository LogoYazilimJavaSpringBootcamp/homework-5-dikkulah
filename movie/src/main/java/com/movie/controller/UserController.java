package com.movie.controller;

import com.movie.dto.UserDto;
import com.movie.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto request){
        return ResponseEntity.ok().body(userService.signUp(request));
    }
    @PutMapping("update")
    public ResponseEntity<UserDto> updateNameAndLastName(@RequestBody UserDto request){
        return ResponseEntity.ok().body(userService.updateFullNameAndPassword(request));
    }
    @PostMapping("login")
    public ResponseEntity<HttpStatus> login(@RequestBody UserDto request)  {
        return ResponseEntity.ok().body(userService.login(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
