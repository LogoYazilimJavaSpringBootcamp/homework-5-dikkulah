package com.movie.service;

import com.movie.dto.CommentDto;
import com.movie.dto.MovieDto;
import com.movie.dto.UserDto;
import com.movie.model.Comment;
import com.movie.model.Movie;
import com.movie.model.User;
import com.movie.repository.CommentRepository;
import com.movie.repository.MovieRepository;
import com.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public CommentDto addComment(CommentDto request, Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        request.setUser(modelMapper.map(user, UserDto.class));
        request.setMovie(modelMapper.map(movie, MovieDto.class));
        Comment savedComment= commentRepository.save(modelMapper.map(request, Comment.class));
        user.getComments().add(savedComment);
        userRepository.save(user);
        movie.getComments().add(savedComment);
        movieRepository.save(movie);

        return modelMapper.map(commentRepository.findById(savedComment.getId()), CommentDto.class);
    }

    public List<CommentDto> getAllComments() {
        return mapList(commentRepository.findAll(),CommentDto.class);
    }
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }
}
