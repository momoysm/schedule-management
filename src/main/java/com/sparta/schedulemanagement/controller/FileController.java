package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    @PostMapping("/files")
    public void uploadFile(@RequestParam("file") MultipartFile[] uploadFiles) {
        log.info("업로드 시작");
        fileService.uploadFile(uploadFiles);
    }

}
