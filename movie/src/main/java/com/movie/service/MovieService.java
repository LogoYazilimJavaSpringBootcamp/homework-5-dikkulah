package com.movie.service;

import com.movie.dto.MovieDto;

import com.movie.model.Movie;

import com.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieDto addMovie(MovieDto movieDto) {
        return modelMapper.map(movieRepository.save(modelMapper.map(movieDto, Movie.class)), MovieDto.class);
    }
    public MovieDto getMovieByTitle(String title) {
        return modelMapper.map(movieRepository.findByTitleContains(title),MovieDto.class);
    }


    public List<MovieDto> getAllMovies() {
        return BaseService.mapList(movieRepository.findAll(),MovieDto.class);
    }



}
