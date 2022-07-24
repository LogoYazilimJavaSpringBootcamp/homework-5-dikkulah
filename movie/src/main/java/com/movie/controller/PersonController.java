package com.movie.controller;

import com.movie.dto.PersonDto;
import com.movie.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("person")
public class PersonController {
    private final PersonService personService;

    // kişi kaydet
    @PostMapping
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto request){
        log.info("add person controller");
        return ResponseEntity.ok().body(personService.addPerson(request));
    }

    //id ile kişi getir
    @GetMapping("{id}")
    public ResponseEntity<PersonDto> getPersonsById(@PathVariable Long id) {
        log.info("get person by ıd controller");
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    //tümünü listele
    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons(){
        log.info("get all persons controller");
        return ResponseEntity.ok().body(personService.getAllPersons());
    }
    // id ile kii silme
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        log.info("delete person by id controller");
        return ResponseEntity.ok().body(personService.deleteById(id));
    }




}
