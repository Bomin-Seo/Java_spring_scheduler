package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.SchedulerRepository;
import com.sparta.scheduler.service.SchedulerService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> updateSchedule(@PathVariable Long id, @RequestBody SchedulerRequestDto requestDto) {
        String inputPassword = requestDto.getPassword();
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        Schedule schedule = schedulerRepository.findById(id);

        if (schedule != null && schedule.getPassword().equals(inputPassword)) {
            SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
            schedulerService.updateSchedule(id, requestDto);
            return ResponseEntity.ok("일정 변경에 성공하였습니다.");
        } else {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
        }
    }

    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        Schedule schedule = schedulerRepository.findById(id);
        if (schedule != null && schedule.getPassword().equals(password)) {
            schedulerService.deleteSchedule(id);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

}
