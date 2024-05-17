package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.SchedulerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.List;


public class SchedulerService {

    private final JdbcTemplate jdbcTemplate;
    private final SchedulerRepository schedulerRepository;

    public SchedulerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.schedulerRepository = new SchedulerRepository(jdbcTemplate);
    }

    public SchedulerResponseDto createSchedule(SchedulerRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        schedulerRepository.save(schedule);
        return new SchedulerResponseDto(schedule);
    }

    public List<SchedulerResponseDto> getAllSchedules() {
        return schedulerRepository.getAllSchedules();
    }

    public ResponseEntity<?> updateSchedule(Long id, String password, SchedulerRequestDto requestDto) {
        Schedule schedule = schedulerRepository.findById(id);
        Date currentDate = schedule.getDate();
        if (schedule != null && schedule.getPassword().equals(password)) {
            schedulerRepository.update(id, requestDto, currentDate, password);
            return ResponseEntity.ok(true);
        } else {return ResponseEntity.ok(false);}
    }

    public ResponseEntity<?> deleteSchedule(Long id, String password) {
        Schedule schedule = schedulerRepository.findById(id);
        if (schedule != null && schedule.getPassword().equals(password)) {
            schedulerRepository.delete(id);
            return ResponseEntity.ok(true);
        }
        else {return ResponseEntity.ok(false);}
    }
}
