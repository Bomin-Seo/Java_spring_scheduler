package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.service.SchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/schedules")
    public SchedulerResponseDto createSchedule(@RequestBody SchedulerRequestDto requestDto){
        return schedulerService.createSchedule(requestDto);
    }

    @GetMapping("/schedules")
    public List<SchedulerResponseDto> getSchedules(){
        return schedulerService.getAllSchedules();
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id, @RequestParam String password, @RequestBody SchedulerRequestDto requestDto) {
        return schedulerService.updateSchedule(id, password, requestDto);
    }

    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
        return schedulerService.deleteSchedule(id, password);
    }
}
