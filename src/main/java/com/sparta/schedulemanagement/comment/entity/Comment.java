package com.sparta.schedulemanagement.comment.entity;

import com.sparta.schedulemanagement.comment.dto.CommentRequestDto;
import com.sparta.schedulemanagement.schedule.entity.Schedule;
import com.sparta.schedulemanagement.timestamped.Timestamped;
import com.sparta.schedulemanagement.user.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, Schedule schedule, User user){
        this.content = requestDto.getContent();
        this.schedule = schedule;
        this.user = user;
    }

    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }

}
