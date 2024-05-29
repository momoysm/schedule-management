package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.exception.NotFoundException;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
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

        if(scheduleRequestDto.getPassword().equals(schedule.getPassword())) {
            schedule.update(scheduleRequestDto);

            return scheduleId;
        }else{
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional
    public Long deleteSchedule(SchedulePasswordRequestDto schedulePasswordRequestDto) {

        Schedule schedule = findScheduleById(schedulePasswordRequestDto.getId());

        if(schedulePasswordRequestDto.getPassword().equals(schedule.getPassword())){
            scheduleRepository.delete(schedule);

            return schedulePasswordRequestDto.getId();
        }else{
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }

    }

    public Schedule findScheduleById(Long scheduleId){
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new NotFoundException("찾으시는 일정은 존재하지 않습니다."));
    }

}
