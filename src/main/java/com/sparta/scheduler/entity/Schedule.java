package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.SchedulerResquestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Schedule {
    private int id;
    private String title;
    private String contents;
    private String admin;
    private int password;
    private Date date;

    public Schedule(SchedulerResquestDto requestDto) {
        this.id = requestDto.getId();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.admin = requestDto.getAdmin();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    public void update(SchedulerResquestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
