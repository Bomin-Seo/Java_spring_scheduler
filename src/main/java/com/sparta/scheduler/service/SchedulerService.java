package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.SchedulerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Component
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }

    public SchedulerResponseDto createSchedule(SchedulerRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        schedulerRepository.save(schedule);
        return new SchedulerResponseDto(schedule);
    }

    public List<SchedulerResponseDto> getAllSchedules() {
        return schedulerRepository.findAll().stream().map(SchedulerResponseDto::new).toList();
    }

    @Transactional
    public ResponseEntity<?> updateSchedule(Long id, String password, SchedulerRequestDto requestDto) {
        Schedule schedule = findScheduleById(id);
        if (schedule.getPassword().equals(password)) {
            schedule.update(requestDto);
            return ResponseEntity.ok(true);
        } else {return ResponseEntity.ok(false);}
    }

    @Transactional
    public ResponseEntity<?> deleteSchedule(Long id, String password) {
        Schedule schedule = findScheduleById(id);

        if (schedule.getPassword().equals(password)) {
            schedulerRepository.delete(schedule);
            return ResponseEntity.ok(true);
        }
        else {return ResponseEntity.ok(false);}
    }

    private Schedule findScheduleById(Long id) {
        return schedulerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Schedule not found")
        );
    }
}
