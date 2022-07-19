package com.movie.controller;

import com.movie.dto.GenreDto;
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

    @PostMapping
    public ResponseEntity<GenreDto> addGenre(@RequestBody GenreDto request){
        return ResponseEntity.ok().body(genreService.addGenre(request));
    }
    @PostMapping("addAll")
    public ResponseEntity<List<GenreDto>> addAllGenre(@RequestBody List<GenreDto> request){
        return ResponseEntity.ok().body(genreService.addAllGenres(request));
    }

    @GetMapping("{name}")
    public ResponseEntity<GenreDto> getGenreByName(@PathVariable String name){
        return ResponseEntity.ok().body(genreService.getGenreByName(name));
    }
    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres(){
        return ResponseEntity.ok().body(genreService.getAllGenres());
    }

    @DeleteMapping("{name}")
    public ResponseEntity<HttpStatus> deleteByName(@PathVariable String name){
        return ResponseEntity.ok().body(genreService.deleteByName(name));
    }
    @PutMapping("{id}/{newName}")
    public ResponseEntity<GenreDto> updateGenreById(@PathVariable Long id, @PathVariable String newName){
        return ResponseEntity.ok().body(genreService.updateNameById(id,newName));
    }


}
