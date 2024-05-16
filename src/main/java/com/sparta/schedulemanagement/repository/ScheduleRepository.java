package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //LocalDateTime으로 정렬하려면 findAll 뒤에 By를 먼저 붙여주면 해결
    List<Schedule> findAllByOrderByCreatedAtDesc();
}
