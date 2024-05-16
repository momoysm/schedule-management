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

    @Column
    private String scheduleTitle;

    @Column
    private String scheduleContent;

    @Column
    private String scheduleManager;

    @Column
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
