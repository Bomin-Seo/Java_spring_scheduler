package com.sparta.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;
    private String userId;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}