package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.service.SchedulerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedules")
    public SchedulerResponseDto createSchedule(@RequestBody SchedulerRequestDto requestDto){
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.createSchedule(requestDto);
    }

    @GetMapping("/schedules")
    public List<SchedulerResponseDto> getSchedules(){
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.getAllSchedules();
    }

    @PutMapping("/schedules/{id}")
    public void updateSchedule(@PathVariable Long id, @RequestBody SchedulerRequestDto requestDto){
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        schedulerService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id){
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        schedulerService.deleteSchedule(id);
    }
}
