package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String userId;
    private final Long scheduleId;
    private final LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.scheduleId = comment.getScheduleId();
        this.createdAt = comment.getCreatedAt();
    }
}
