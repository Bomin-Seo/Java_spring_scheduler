package com.sparta.scheduler.repository;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;


public class SchedulerRepository {
    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO schedule (title, contents, admin, password, date) VALUES (?, ?, ?, ?, ?)";

        Date sqlDate = new Date(schedule.getDate().getTime());

        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setString(3, schedule.getAdmin());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setDate(5, sqlDate);
                    return preparedStatement;
                },
                keyHolder);
        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        schedule.setId(id);
        return schedule;
    }

    public List<SchedulerResponseDto> getAllSchedules() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<SchedulerResponseDto>() {
            public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Schedule schedule = setInfo(rs);
                return new SchedulerResponseDto(schedule);
            }
        });
    }

    public Schedule findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {return setInfo(resultSet);}
            else {return null;}
        }, id);
    }

    public Schedule setInfo(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule(new SchedulerRequestDto());
        schedule.setId(rs.getLong("id"));
        schedule.setTitle(rs.getString("title"));
        schedule.setContents(rs.getString("contents"));
        schedule.setAdmin(rs.getString("admin"));
        schedule.setPassword(rs.getString("password"));
        schedule.setDate(rs.getDate("date"));
        return schedule;
    }

    public void update(Long id, SchedulerRequestDto requestDto) {
        String sql = "UPDATE schedule SET title=?, contents=?, admin=?, password=?, date=? WHERE id=?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getAdmin(), requestDto.getPassword(), requestDto.getDate(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

