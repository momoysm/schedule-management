package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/createSchedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @GetMapping("/getSchedule")
    public ScheduleResponseDto getSchedule(@RequestParam Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/getAllSchedule")
    public List<ScheduleResponseDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @PutMapping("/updateSchedule/{scheduleId}")
    public Long updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDto);
    }

    @DeleteMapping("/deleteSchedule")
    public Long deleteSchedule(@RequestBody SchedulePasswordRequestDto schedulePasswordRequestDto) {
        return scheduleService.deleteSchedule(schedulePasswordRequestDto);
    }

}
