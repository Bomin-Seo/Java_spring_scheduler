package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SchedulerResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String admin;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

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
