package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    public Schedule(ScheduleRequestDto scheduleRequestDto, User user){
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
        this.user = user;
    }

    public void update(ScheduleRequestDto scheduleRequestDto, Image image){
        this.title = scheduleRequestDto.getTitle();
        this.content = scheduleRequestDto.getContent();
        this.image = image;
    }

}
