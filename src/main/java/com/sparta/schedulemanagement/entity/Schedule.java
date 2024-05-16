package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(name="scheduleTitle", nullable = false)
    private String scheduleTitle;

    @Column(name = "scheduleContent")
    private String scheduleContent;

    @Column(name="scheduleManager", nullable = false)
    private String scheduleManager;

    @Column(name="password", nullable = false)
    private String password;

    public Schedule(ScheduleRequestDto scheduleRequestDto){
        this.scheduleTitle = scheduleRequestDto.getScheduleTitle();
        this.scheduleContent = scheduleRequestDto.getScheduleContent();
        this.scheduleManager = scheduleRequestDto.getScheduleManager();
        this.password = scheduleRequestDto.getPassword();
    }

    public void update(ScheduleRequestDto scheduleRequestDto){
        this.scheduleTitle = scheduleRequestDto.getScheduleTitle();
        this.scheduleContent = scheduleRequestDto.getScheduleContent();
        this.scheduleManager = scheduleRequestDto.getScheduleManager();
        this.password = scheduleRequestDto.getPassword();
    }

}
