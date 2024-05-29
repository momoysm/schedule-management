package com.sparta.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${com.example.upload.path}") // application.properties의 변수
    private String uploadPath;

    public void uploadFile(MultipartFile[] uploadFiles) {
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
                log.info("업로드 완료");
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

}
