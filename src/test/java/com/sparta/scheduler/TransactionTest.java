package com.sparta.scheduler;

import com.sparta.scheduler.entity.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.sql.Date;

@SpringBootTest
public class TransactionTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("메모 생성 성공")
    void test1() {
        Schedule schedule = new Schedule();
        schedule.setTitle("title");
        schedule.setContents("@Transactional 테스트 중!");
        schedule.setAdmin("admin");
        schedule.setPassword("1234");

        LocalDate localDate = LocalDate.of(2024, 2, 21);
        Date date = Date.valueOf(localDate);
        schedule.setDate(date);

        em.persist(schedule);
    }

}
