package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.exception.NotFoundException;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto, User user) {

        Schedule schedule = scheduleRepository.save(new Schedule(scheduleRequestDto, user));

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public ScheduleResponseDto getSchedule(Long scheduleId, User user) {

        Schedule schedule = findScheduleByIdAndUser(scheduleId, user); // 해당 일정이 존재하는지 확인

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getAllSchedule(User user) {

        return scheduleRepository.findByUserOrderByCreatedAtDesc(user).stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto, User user) {

        Schedule schedule = findScheduleByIdAndUser(scheduleId, user);

        if(scheduleRequestDto.getPassword().equals(schedule.getPassword())) {
            schedule.update(scheduleRequestDto);

            return scheduleId;
        }else{
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional
    public Long deleteSchedule(SchedulePasswordRequestDto schedulePasswordRequestDto, User user) {

        Schedule schedule = findScheduleByIdAndUser(schedulePasswordRequestDto.getId(), user);

        if(schedulePasswordRequestDto.getPassword().equals(schedule.getPassword())){
            scheduleRepository.delete(schedule);

            return schedulePasswordRequestDto.getId();
        }else{
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        }

    }

    // scheduleId와 user에 해당하는 schedule조회
    public Schedule findScheduleByIdAndUser(Long scheduleId, User user){

        return scheduleRepository.findByIdAndUser(scheduleId, user).orElseThrow(
                () -> new NotFoundException("찾으시는 일정은 존재하지 않습니다.")
        );
    }

}
