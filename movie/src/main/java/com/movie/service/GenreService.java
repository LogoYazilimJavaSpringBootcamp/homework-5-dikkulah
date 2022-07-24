package com.movie.service;

import com.movie.dto.GenreDto;
import com.movie.dto.MovieDto;
import com.movie.model.Genre;
import com.movie.repository.GenreRepository;
import com.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public GenreDto addGenre(GenreDto request) {
        log.info("genre kaydedildi.");
        return modelMapper.map(genreRepository.save(modelMapper.map(request, Genre.class)), GenreDto.class);
    }

    public List<GenreDto> addAllGenres(List<GenreDto> request) {
        List<GenreDto> list = new ArrayList<>();
        request.forEach(genreDto -> list.add(addGenre(genreDto)));
        log.info("tüm genreler kaydedildi");
        return list;
    }

    public GenreDto getGenreById(Long id) {
        log.info("id ile genre listelendi");
        return modelMapper.map(genreRepository.findById(id), GenreDto.class);
    }

    public List<GenreDto> getAllGenres() {
        log.info("tüm genreler listelendi");
        return mapList(genreRepository.findAll(), GenreDto.class);
    }

    public HttpStatus deleteByName(String name) {
        log.info("genre adı ile gelen ilk genre silindi.");
        genreRepository.findByName(name).ifPresent(genreRepository::delete);
        return HttpStatus.ACCEPTED;
    }

    public GenreDto updateNameById(Long id, String newName) {
        genreRepository.findById(id).ifPresent(genre -> {
            genre.setName(newName);
            genreRepository.save(genre);
            log.info("genre güncellendi.");
        });
        return modelMapper.map(genreRepository.findByName(newName), GenreDto.class);
    }

    public MovieDto addGenreToMovie(GenreDto request, Long movieId) {
        var movie = movieRepository.findById(movieId).orElseThrow();
        movie.getGenres().add(modelMapper.map(request, Genre.class));
        log.info("filme genre eklendi..");
        return modelMapper.map(movieRepository.save(movie), MovieDto.class);

    }

    // Liste için Dto-Entity dönüşümü.
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }
}
