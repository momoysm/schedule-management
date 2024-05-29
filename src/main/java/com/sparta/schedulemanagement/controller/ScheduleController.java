package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto scheduleRequestDto){

        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @GetMapping("/schedule")
    public ScheduleResponseDto getSchedule(@RequestParam Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @PutMapping("/schedule/{scheduleId}")
    public Long updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDto);
    }

    @DeleteMapping("/schedule")
    public Long deleteSchedule(@RequestBody @Valid SchedulePasswordRequestDto schedulePasswordRequestDto) {
        return scheduleService.deleteSchedule(schedulePasswordRequestDto);
    }

}
