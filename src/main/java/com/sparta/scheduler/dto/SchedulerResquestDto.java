package com.sparta.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SchedulerResquestDto {
    private int id;
    private String title;
    private String contents;
    private String admin;
    private int password;
    private Date date;


}
