package com.movie.service;

import com.movie.dto.CommentDto;
import com.movie.dto.MovieDto;
import com.movie.dto.UserDto;
import com.movie.model.Comment;
import com.movie.model.type.MembershipType;
import com.movie.repository.CommentRepository;
import com.movie.repository.MovieRepository;
import com.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public List<CommentDto> addComment(CommentDto request, Long userId, Long movieId) {
        userRepository.findById(userId).ifPresent(user -> {
            if (user.getMembershipType() != MembershipType.FREE) {
                movieRepository.findById(movieId).ifPresent(movie -> {
                    request.setMovie(modelMapper.map(movie, MovieDto.class));
                    request.setUser(modelMapper.map(user, UserDto.class));
                    Comment comment = commentRepository.save(modelMapper.map(request, Comment.class));
                    user.getComments().add(comment);
                    movie.getComments().add(comment);
                    userRepository.save(user);
                    movieRepository.save(movie);
                    log.info("yorum kaydedildi. "+request);
                });
            }else {
                log.info("yorum kaydedilmedi.");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SADECE ÜCRETLİ KULLANICILAR YORUM YAPABİLİR");
            }
        });
        return mapList(movieRepository.findById(movieId).orElseThrow().getComments(),CommentDto.class);
    }

    public List<CommentDto> getAllComments() {
        log.info("tüm yorumlar listelendi.");
        return mapList(commentRepository.findAll(),CommentDto.class);
    }

    // Liste için Dto-Entity dönüşümü.
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }
}
