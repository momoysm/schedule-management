package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, Schedule schedule){
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
        this.schedule = schedule;
    }

    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
        this.userId = requestDto.getUserId();
    }

}
