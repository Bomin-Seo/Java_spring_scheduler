package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.CommentRequestDto;
import com.sparta.scheduler.dto.CommentResponseDto;
import com.sparta.scheduler.entity.Comment;
import com.sparta.scheduler.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createcomment(requestDto);
    }

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{scheduleId}")
    public List<CommentResponseDto> getCommentsForSchedule(@PathVariable Long scheduleId) {
        return commentService.getCommentsForSchedule(scheduleId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto updatedComment = commentService.updateComment(id, requestDto);
        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        boolean isDeleted = commentService.deleteComment(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
