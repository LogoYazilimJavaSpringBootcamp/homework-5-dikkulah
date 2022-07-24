package com.movie.controller;

import com.movie.dto.GenreDto;
import com.movie.dto.MovieDto;
import com.movie.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor@Log4j2
@RestController
@RequestMapping("genre")
public class GenreController {

    private final GenreService genreService;

    //genre ekleme
    @PostMapping
    public ResponseEntity<GenreDto> addGenre(@RequestBody GenreDto request){
        log.info("add genre controller");
        return ResponseEntity.ok().body(genreService.addGenre(request));
    }
    //liste şeklinde genre ekleme
    @PostMapping("/addAll")
    public ResponseEntity<List<GenreDto>> addAllGenre(@RequestBody List<GenreDto> request){
        log.info("add all controller");
        return ResponseEntity.ok().body(genreService.addAllGenres(request));
    }
    //filme genre ekleme
    @PostMapping("/toMovie/{movieId}")
    public ResponseEntity<MovieDto> addGenreToMovie(@RequestBody GenreDto request,@PathVariable Long movieId){
        log.info("add genre to movie controller");
        return ResponseEntity.ok().body(genreService.addGenreToMovie(request,movieId));
    }
    //get genre by ıd

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id){
        log.info("get genre by ıd contoller");
        return ResponseEntity.ok().body(genreService.getGenreById(id));
    }
    //tüm genreler
    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres(){
        log.info("get all gnres controller");
        return ResponseEntity.ok().body(genreService.getAllGenres());
    }
    //isimle silme

    @DeleteMapping("/{name}")
    public ResponseEntity<HttpStatus> deleteByName(@PathVariable String name){
        log.info("delete by name controller");
        return ResponseEntity.ok().body(genreService.deleteByName(name));
    }
    //update genre
    @PutMapping("/{id}/{newName}")
    public ResponseEntity<GenreDto> updateGenreById(@PathVariable Long id, @PathVariable String newName){
        log.info("update genre by ıd controller");
        return ResponseEntity.ok().body(genreService.updateNameById(id,newName));
    }


}
