package com.movie.service;

import com.movie.dto.PersonDto;
import com.movie.model.Person;
import com.movie.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor@Log4j2
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonDto addPerson(PersonDto request) {
        return modelMapper.map(personRepository.save(modelMapper.map(request, Person.class)),PersonDto.class);
    }

    public List<PersonDto> addAllPersons(List<PersonDto> request) {
        return null;
    }

    public List<PersonDto> getPersonsByName(String name) {
        return null;
    }

    public List<PersonDto> getAllPersons() {
            return null;
    }

    public HttpStatus deleteById(Long id) {
        return null;
    }
}
