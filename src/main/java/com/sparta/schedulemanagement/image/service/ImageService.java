package com.sparta.schedulemanagement.image.service;

import com.sparta.schedulemanagement.image.entity.Image;
import com.sparta.schedulemanagement.schedule.entity.Schedule;
import com.sparta.schedulemanagement.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image uploadFile(MultipartFile file, Schedule schedule) throws IOException{

        if(schedule.getImage() != null){
            Image image = schedule.getImage();
            image.update(file.getOriginalFilename(), file.getContentType(), (int) file.getSize(), file.getBytes());
            return image;
        }else{
            return new Image(file.getOriginalFilename(), file.getContentType(), (int) file.getSize(), file.getBytes());
        }
    }
}
