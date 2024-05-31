package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.security.UserDetailsImpl;
import com.sparta.schedulemanagement.service.FileService;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestPart("dto") @Valid ScheduleRequestDto scheduleRequestDto,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestPart("file") MultipartFile file){

        return scheduleService.createSchedule(scheduleRequestDto, userDetails.getUser(), file);
    }

    @GetMapping("/schedule")
    public ScheduleResponseDto getSchedule(@RequestParam Long scheduleId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.getSchedule(scheduleId, userDetails.getUser());
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedule(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.getAllSchedule(userDetails.getUser());
    }

    @PutMapping("/schedule/{scheduleId}")
    public Long updateSchedule(@PathVariable Long scheduleId,
                               @RequestPart("dto") @Valid ScheduleRequestDto scheduleRequestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails,
                               @RequestPart("file") MultipartFile file){
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDto, userDetails.getUser(), file);
    }

    @DeleteMapping("/schedule")
    public Long deleteSchedule(@RequestBody @Valid SchedulePasswordRequestDto schedulePasswordRequestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.deleteSchedule(schedulePasswordRequestDto, userDetails.getUser());
    }

}
