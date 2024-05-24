package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name = "admin", nullable = false)
    private String admin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt; // 변경된 필드명

    public Schedule(SchedulerRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.admin = requestDto.getAdmin();
        this.password = requestDto.getPassword();
        this.createdAt = requestDto.getCreatedAt(); // 변경된 필드명
    }

    public void update(SchedulerRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.admin = requestDto.getAdmin();
        this.createdAt = LocalDateTime.now(); // 변경된 필드명
    }
}
