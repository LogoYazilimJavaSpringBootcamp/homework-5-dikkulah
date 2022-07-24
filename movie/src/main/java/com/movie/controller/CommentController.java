package com.movie.controller;

import com.movie.dto.CommentDto;
import com.movie.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("{userId}/{movieId}")
    public ResponseEntity<List<CommentDto>> addComment(@RequestBody CommentDto request, @PathVariable Long userId, @PathVariable Long movieId){
        return ResponseEntity.ok().body(commentService.addComment(request,userId,movieId));

    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

}
