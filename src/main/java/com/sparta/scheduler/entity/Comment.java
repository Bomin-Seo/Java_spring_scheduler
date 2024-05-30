package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Long scheduleId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "scheduleId", insertable = false, updatable = false)
    private Schedule schedule;

    public Comment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        this.userId = commentRequestDto.getUserId();
        this.scheduleId = commentRequestDto.getScheduleId();
        this.createdAt = commentRequestDto.getCreatedAt();
    }
}