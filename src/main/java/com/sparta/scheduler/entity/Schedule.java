package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name = "admin", nullable = false) // 변경된 부분
    private String admin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(SchedulerRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.admin = requestDto.getAdmin();
        this.password = requestDto.getPassword();
        this.createdAt = requestDto.getCreatedAt();
    }

    public void update(SchedulerRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.createdAt = LocalDateTime.now();
    }
}
