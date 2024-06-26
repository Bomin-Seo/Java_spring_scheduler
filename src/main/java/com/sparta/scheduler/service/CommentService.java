package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.CommentRequestDto;
import com.sparta.scheduler.dto.CommentResponseDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Comment;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponseDto createcomment(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        comment.setUsername(requestDto.getUsername());
        Comment savedcomment = commentRepository.save(comment);
        return new CommentResponseDto(savedcomment);
    }

    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.setContent(requestDto.getContent());
        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<?> deleteComment(Long id) {
        Comment comment = findScheduleById(id);
        commentRepository.delete(comment);
        return ResponseEntity.ok(true);
    }

    public List<CommentResponseDto> getCommentsForSchedule(Long scheduleId) {
        return commentRepository.findByScheduleIdOrderByCreatedAt(scheduleId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    private Comment findScheduleById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("일정을 찾을 수 없습니다.")
        );
    }
}