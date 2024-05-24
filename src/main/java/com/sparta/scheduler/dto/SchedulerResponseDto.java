package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String admin;
    private final String password;
    private final LocalDateTime createdAt; // 변경된 필드명
    private final LocalDateTime modifiedAt;

    public SchedulerResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.admin = schedule.getAdmin();
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
