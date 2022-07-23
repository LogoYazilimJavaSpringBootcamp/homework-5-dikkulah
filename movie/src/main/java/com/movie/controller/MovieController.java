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

    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getMovieByTitle(id));
    }

    @PostMapping("{email}")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto request, @PathVariable String email) {
        return ResponseEntity.ok().body(movieService.addMovie(request, email));
    }

    @PostMapping("writer/{movieId}")
    public ResponseEntity<MovieDto> addWriterToMovie(@RequestBody MovieDto request, @PathVariable Long movieId) {
        return ResponseEntity.ok().body(movieService.addWriter(request));
    }

    @PostMapping("director/{movieId}")
    public ResponseEntity<MovieDto> addDirectorToMovie(@RequestBody MovieDto request, @PathVariable Long movieId) {
        return ResponseEntity.ok().body(movieService.addDirector(request));
    }

    @PostMapping("actor/{movieId}")
    public ResponseEntity<MovieDto> addActorToMovie(@RequestBody MovieDto request, @PathVariable Long movieId) {
        return ResponseEntity.ok().body(movieService.addActor(request));
    }


}
