package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Schedule schedule = findScheduleById(scheduleId);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getAllSchedule() {

        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = findScheduleById(scheduleId);

        if(scheduleRequestDto.getPassword().equals(schedule.getPassword())){
            schedule.update(scheduleRequestDto);
        }

        return scheduleId;
    }

    public Long deleteSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = findScheduleById(scheduleId);

        if(scheduleRequestDto.getPassword().equals(schedule.getPassword())){
            scheduleRepository.delete(schedule);
        }

        return scheduleId;
    }

    public Schedule findScheduleById(Long scheduleId){
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("찾으시는 일정은 존재하지 않습니다."));
    }
}
