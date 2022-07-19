package com.movie.service;


import com.movie.dto.EmailDto;
import com.movie.dto.MovieDto;
import com.movie.dto.UserDto;
import com.movie.model.Movie;
import com.movie.repository.MovieRepository;
import com.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AmqpTemplate amqpTemplate;

    public MovieDto addMovie(MovieDto movieDto, String email) {
        //her kullanıcıya mail gönderir.
        userRepository.findAll().forEach(user -> {
            EmailDto emailDto = new EmailDto();
            emailDto.setFrom(email);
            emailDto.setDescription(movieDto.getTitle() + " isimli film eklendi.");
            emailDto.setTo(user.getEmail());
            emailDto.setTitle(movieDto.getTitle());
            emailDto.setSendingTime(LocalDateTime.now().toString());
            amqpTemplate.convertAndSend(emailDto);
        });

        var userDto = modelMapper.map(userRepository.findByEmail(email).orElseThrow(), UserDto.class);
        movieDto.setUser(userDto);
        return modelMapper.map(movieRepository.save(modelMapper.map(movieDto, Movie.class)), MovieDto.class);
    }

    public MovieDto getMovieByTitle(String title) {
        return modelMapper.map(movieRepository.findByTitleContains(title), MovieDto.class);
    }


    public List<MovieDto> getAllMovies() {
        return mapList(movieRepository.findAll(), MovieDto.class);
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

}
