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
    // tüm filmleri listele
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        log.info("get all movies");
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.ACCEPTED);
    }
    // id ile çağır
    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        log.info("get movie by ıd");
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }
    // usera film ekle
    @PostMapping("{email}")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto request, @PathVariable String email) {
        log.info("add movie controller");
        return ResponseEntity.ok().body(movieService.addMovie(request, email));
    }

    // filme yazar ekle
    @PostMapping("writer/{movieId}")
    public ResponseEntity<MovieDto> addWriterToMovie(@RequestBody PersonDto request, @PathVariable Long movieId) {
        log.info("add writer to movie controller");
        return ResponseEntity.ok().body(movieService.addWriter(request,movieId));
    }
    // filme yönetmen ekle
    @PostMapping("director/{movieId}")
    public ResponseEntity<MovieDto> addDirectorToMovie(@RequestBody PersonDto request, @PathVariable Long movieId) {
        log.info("add director to movie controller");
        return ResponseEntity.ok().body(movieService.addDirector(request,movieId));
    }
    // filme oyuncu ekle
    @PostMapping("actor/{movieId}")
    public ResponseEntity<MovieDto> addActorToMovie(@RequestBody PersonDto request, @PathVariable Long movieId) {
        log.info("add actor to movie controller");
        return ResponseEntity.ok().body(movieService.addActor(request,movieId));
    }
    // filmin yorumlarını listele
    @GetMapping("comments/{movieId}")
    public ResponseEntity<List<CommentDto>> getCommentsOfMovie(@PathVariable Long movieId){
        log.info("get comments of movie controller");
        return ResponseEntity.ok().body(movieService.getCommentsOfMovie(movieId));
    }


}
