package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.CommentRepository;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {

        Schedule schedule = existSchedule(scheduleId);

        Comment comment = commentRepository.save(new Comment(requestDto, schedule));

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {

        Schedule schedule = existSchedule(scheduleId);

        Comment comment = existComment(schedule, commentId);
        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    public Schedule existSchedule(Long scheduleId) {

        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NullPointerException("선택한 일정이 존재하지 않습니다.")
        );
    }

    public Comment existComment(Schedule schedule, Long commentId) {

        Optional<Comment> optionalComment = commentRepository.findByCommentIdAndSchedule(commentId, schedule);

        if(optionalComment.isPresent()){
            return optionalComment.get();
        }else{
            throw new NullPointerException("선택한 일정에 해당 댓글이 존재하지 않습니다.");
        }
    }

}
