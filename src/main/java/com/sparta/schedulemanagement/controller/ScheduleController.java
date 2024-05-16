package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.SchedulePasswordRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Value("${com.example.upload.path}") // application.properties의 변수
    private String uploadPath;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/createSchedule")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto scheduleRequestDto){

        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {

            // 이미지 파일만 업로드 가능
            if(!uploadFile.getContentType().startsWith("image")){
                return;
            }

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();

            String fileName = "";
            if (originalName != null) {
                fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            }

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 중간에 "_"를 이용해 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
            }catch (IOException e){
                throw new RuntimeException("업로드에 실패했습니다.");
            }
        }
    }

    private String makeFolder() {

        String folderPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        // make folder
        File uploadPatheFolder = new File(uploadPath,folderPath);

        if(!uploadPatheFolder.exists()){
            uploadPatheFolder.mkdirs();
        }

        return folderPath;
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
    public Long updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDto);
    }

    @DeleteMapping("/deleteSchedule")
    public Long deleteSchedule(@RequestBody @Valid SchedulePasswordRequestDto schedulePasswordRequestDto) {
        return scheduleService.deleteSchedule(schedulePasswordRequestDto);
    }

}
