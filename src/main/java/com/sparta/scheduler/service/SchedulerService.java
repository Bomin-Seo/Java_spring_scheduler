package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.entity.User;
import com.sparta.scheduler.repository.SchedulerRepository;
import com.sparta.scheduler.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;
    private final UserRepository userRepository;

    public SchedulerService(SchedulerRepository schedulerRepository, UserRepository userRepository) {
        this.schedulerRepository = schedulerRepository;
        this.userRepository = userRepository;
    }

    public SchedulerResponseDto createSchedule(SchedulerRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        schedule.setAdmin(requestDto.getAdmin());
        Schedule savedSchedule = schedulerRepository.save(schedule);
        return new SchedulerResponseDto(savedSchedule);
    }

    public List<SchedulerResponseDto> getAllSchedules() {
        return schedulerRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(SchedulerResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<?> updateSchedule(Long id, String password, SchedulerRequestDto requestDto) {
        Schedule schedule = findScheduleById(id);
        if (schedule.getPassword().equals(password)) {
            schedule.update(requestDto);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @Transactional
    public ResponseEntity<?> deleteSchedule(Long id, String password) {
        Schedule schedule = findScheduleById(id);

        if (schedule.getPassword().equals(password)) {
            schedulerRepository.delete(schedule);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    private Schedule findScheduleById(Long id) {
        return schedulerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("일정을 찾을 수 없습니다.")
        );
    }

    public List<Schedule> getSchedulesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        return user.getSchedules();
    }
}
