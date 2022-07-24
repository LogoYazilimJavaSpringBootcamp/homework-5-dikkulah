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
        return modelMapper.map(personRepository.save(modelMapper.map(request, Person.class)), PersonDto.class);
    }

    public PersonDto getPersonById(Long id) {
        var person = personRepository.findById(id).orElseThrow();
        return modelMapper.map(person, PersonDto.class);
    }

    public List<PersonDto> getAllPersons() {
        return mapList(personRepository.findAll(), PersonDto.class);
    }

    public HttpStatus deleteById(Long id) {
        personRepository.findById(id).ifPresent(personRepository::delete);
        return HttpStatus.ACCEPTED;
    }


    // Liste için Dto-Entity dönüşümü.
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

}
