package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String admin;
    private String password;
    private Date date;

    public Schedule(SchedulerRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.admin = requestDto.getAdmin();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}
