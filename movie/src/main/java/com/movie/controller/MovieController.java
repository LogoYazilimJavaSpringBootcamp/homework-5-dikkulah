package com.movie.controller;


import com.movie.dto.CommentDto;
import com.movie.dto.MovieDto;
import com.movie.dto.PersonDto;
import com.movie.service.CommentService;
import com.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor@Slf4j
@RequestMapping("movie")
public class MovieController {

    private final MovieService movieService;
    private final CommentService commentService;
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
    public ResponseEntity<MovieDto> addWriterToMovie(@RequestBody PersonDto request, @PathVariable Long movieId) {
        return ResponseEntity.ok().body(movieService.addWriter(request,movieId));
    }

    @PostMapping("director/{movieId}")
    public ResponseEntity<MovieDto> addDirectorToMovie(@RequestBody PersonDto request, @PathVariable Long movieId) {
        return ResponseEntity.ok().body(movieService.addDirector(request,movieId));
    }

    @PostMapping("actor/{movieId}")
    public ResponseEntity<MovieDto> addActorToMovie(@RequestBody PersonDto request, @PathVariable Long movieId) {
        log.info("controllera girdi");
        return ResponseEntity.ok().body(movieService.addActor(request,movieId));
    }
    @GetMapping("comments/{movieId}")
    public ResponseEntity<List<CommentDto>> getCommentsOfMovie(@PathVariable Long movieId){
        return ResponseEntity.ok().body(movieService.getCommentsOfMovie(movieId));
    }


}
