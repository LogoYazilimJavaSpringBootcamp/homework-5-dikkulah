package com.movie.service;


import com.movie.dto.*;
import com.movie.model.Comment;
import com.movie.model.Movie;
import com.movie.model.Person;
import com.movie.model.User;
import com.movie.model.type.MembershipType;
import com.movie.model.type.PersonType;
import com.movie.repository.MovieRepository;
import com.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        // 3 ADET FİLM KONTROLÜ
        var userDto = modelMapper.map(userRepository.findByEmail(email).orElseThrow(), UserDto.class);
        Movie movie;
        if (userDto.getMembershipType().equals(MembershipType.FREE) && userDto.getAddingMovieRight() > 0) {
            movieDto.setUser(userDto);
            movie = movieRepository.save(modelMapper.map(movieDto, Movie.class));
            userDto.setAddingMovieRight(userDto.getAddingMovieRight() - 1);
            userRepository.save(modelMapper.map(userDto, User.class));
        } else if (userDto.getMembershipType() != MembershipType.FREE) {
            movieDto.setUser(userDto);
            movie = movieRepository.save(modelMapper.map(movieDto, Movie.class));
            userRepository.save(modelMapper.map(userDto, User.class));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"FİLM EKLEME HAKKINIZ BİTTİ VEYA HATA OLUŞTU");
        }
        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto getMovieByTitle(Long id) {
        return modelMapper.map(movieRepository.findById(id), MovieDto.class);
    }


    public List<MovieDto> getAllMovies() {
        return mapList(movieRepository.findAll(), MovieDto.class);
    }

    public MovieDto addWriter(PersonDto request, Long movieId) {

        movieRepository.findById(movieId).ifPresent(movie -> {
            if (request.getJobs().contains(PersonType.WRITER)) {
                movie.getWriters().add(modelMapper.map(request, Person.class));
                movieRepository.save(movie);
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"YAZAR EKELENEMEDİ");
        });
        return modelMapper.map(movieRepository.findById(movieId).orElseThrow(), MovieDto.class);
    }

    public MovieDto addDirector(PersonDto request, Long movieId) {
        movieRepository.findById(movieId).ifPresent(movie -> {
            if (request.getJobs().contains(PersonType.DIRECTOR)) {
                movie.getDirectors().add(modelMapper.map(request, Person.class));
                movieRepository.save(movie);
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"YÖNETMEN EKLENEMEDİ");
        });
        return modelMapper.map(movieRepository.findById(movieId).orElseThrow(), MovieDto.class);
    }

    public MovieDto addActor(PersonDto request, Long movieId) {
        log.info("add actora ulaştı");
        movieRepository.findById(movieId).ifPresent(movie -> {
            if (request.getJobs().contains(PersonType.ACTOR)) {
                movie.getActors().add(modelMapper.map(request, Person.class));
                movieRepository.save(movie);
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"OYUNCU EKLENEMEDİ");
        });
        return modelMapper.map(movieRepository.findById(movieId).orElseThrow(), MovieDto.class);
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

    public List<CommentDto> getCommentsOfMovie(Long movieId) {
        List<Comment> comments = new ArrayList<>();
        movieRepository.findById(movieId).ifPresent(movie -> comments.addAll(movie.getComments()));
        return mapList(comments,CommentDto.class);
    }
}
