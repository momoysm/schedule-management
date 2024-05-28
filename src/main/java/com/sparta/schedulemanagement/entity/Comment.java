package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, Schedule schedule){
        this.comment = requestDto.getComment();
        this.userId = requestDto.getUserId();
        this.schedule = schedule;
    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.userId = requestDto.getUserId();
    }

}
