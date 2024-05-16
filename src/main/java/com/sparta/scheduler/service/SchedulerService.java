package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.SchedulerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


public class SchedulerService {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SchedulerResponseDto createSchedule(SchedulerRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        schedulerRepository.save(schedule);
        return new SchedulerResponseDto(schedule);
    }

    public List<SchedulerResponseDto> getAllSchedules() {
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        return schedulerRepository.getAllSchedules();
    }

    public void updateSchedule(Long id, SchedulerRequestDto requestDto) {
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        Schedule matchedSchedule = schedulerRepository.findById(id);

        if (matchedSchedule == null) {
            throw new RuntimeException("일정을 찾을 수 없습니다: " + id);
        } else {
            schedulerRepository.update(id, requestDto);
        }
    }

    public void deleteSchedule(Long id) {
        SchedulerRepository schedulerRepository = new SchedulerRepository(jdbcTemplate);
        Schedule matchedSchedule = schedulerRepository.findById(id);
        if (matchedSchedule == null) {
            throw new RuntimeException("일정을 찾을 수 없습니다: " + id);
        } else {
            schedulerRepository.delete(id);
        }
    }
}
