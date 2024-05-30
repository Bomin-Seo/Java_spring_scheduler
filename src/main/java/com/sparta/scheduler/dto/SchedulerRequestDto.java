package com.sparta.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SchedulerRequestDto {
    private String title;
    private String contents;
    private String admin;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}