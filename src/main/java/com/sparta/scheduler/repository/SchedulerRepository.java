package com.sparta.scheduler.repository;

import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulerRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();

    List<Schedule> findByAdmin(User admin);
}
