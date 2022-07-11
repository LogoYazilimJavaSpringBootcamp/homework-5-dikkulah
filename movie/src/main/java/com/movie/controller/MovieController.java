package com.movie.controller;

import com.movie.dto.MovieDto;
import com.movie.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }
    @GetMapping("{title}")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(movieService.getMovieByTitle(title));
    }
    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok().body(movieService.addMovie(movieDto));
    }
    @PutMapping
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok().body(movieService.addMovie(movieDto));
    }

}
