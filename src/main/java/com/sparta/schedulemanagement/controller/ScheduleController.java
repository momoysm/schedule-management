package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.security.UserDetailsImpl;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto scheduleRequestDto,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){

        return scheduleService.createSchedule(scheduleRequestDto, userDetails.getUser());
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
                               @RequestBody @Valid ScheduleRequestDto scheduleRequestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/schedule")
    public Long deleteSchedule(@RequestBody @Valid SchedulePasswordRequestDto schedulePasswordRequestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.deleteSchedule(schedulePasswordRequestDto, userDetails.getUser());
    }

}
