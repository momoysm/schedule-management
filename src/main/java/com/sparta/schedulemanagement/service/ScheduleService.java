package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = new Schedule(scheduleRequestDto);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(savedSchedule);

        return scheduleResponseDto;
    }

    public ScheduleResponseDto getSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).get();

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getAllSchedule() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }
}
