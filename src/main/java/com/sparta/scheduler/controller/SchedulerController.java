package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.dto.SchedulerResquestDto;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {
    @PostMapping("/schedules")
    public SchedulerResponseDto createSchedule(@RequestBody SchedulerResquestDto requestDto){
        Schedule schedule = new Schedule(requestDto);
        return new SchedulerResponseDto(schedule);
    }

    @GetMapping("/schedules")
    public List<Schedule> getSchedules(){
        List<Schedule> schedules = new ArrayList<>();
        return null;
    }

    @PutMapping("/schedules/{id}")
    public SchedulerResponseDto updateSchedule(@PathVariable int id, @RequestBody SchedulerResquestDto requestDto){
        return null;
    }

    @DeleteMapping("/schedules/{id}")
    public SchedulerResponseDto deleteSchedule(@PathVariable int id){
        return null;
    }
}
