package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByIdAndUser(Long scheduleId, User user);

    List<Schedule> findByUserOrderByCreatedAtDesc(User user);
}
