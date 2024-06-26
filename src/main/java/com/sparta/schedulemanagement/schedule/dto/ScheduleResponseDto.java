package com.sparta.schedulemanagement.schedule.dto;

import com.sparta.schedulemanagement.image.entity.Image;
import com.sparta.schedulemanagement.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private Image image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.image = schedule.getImage();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

}
