package com.movie.controller;


import com.movie.dto.MovieDto;
import com.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.ACCEPTED);
    }
    @GetMapping("{title}")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(movieService.getMovieByTitle(title));
    }
    @PostMapping("{email}")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto request,@PathVariable String email) {
        return ResponseEntity.ok().body(movieService.addMovie(request,email));
    }



}
