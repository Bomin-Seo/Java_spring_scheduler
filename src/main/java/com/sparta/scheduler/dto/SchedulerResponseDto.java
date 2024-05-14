package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class SchedulerResponseDto {
    private int id;
    private final String title;
    private final String contents;
    private final String admin;
    private final int password;
    private final Date date;

    public SchedulerResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.admin = schedule.getAdmin();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }
}
