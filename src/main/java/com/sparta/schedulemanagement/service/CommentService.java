package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.exception.NotFoundException;
import com.sparta.schedulemanagement.repository.CommentRepository;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        Comment comment = existComment(schedule, commentId, requestDto.getUserId());
        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long scheduleId, Long commentId, String userId) {

        Schedule schedule = existSchedule(scheduleId);

        Comment comment = existComment(schedule, commentId, userId);
        commentRepository.delete(comment);

        return new ResponseEntity<>("삭제 성공!, HttpStatus코드 : " +  HttpStatus.OK, HttpStatus.OK);
    }

    public Schedule existSchedule(Long scheduleId) {

        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NotFoundException("선택한 일정이 존재하지 않습니다.")
        );
    }

    public Comment existComment(Schedule schedule, Long commentId, String userId) {

        Optional<Comment> optionalComment = commentRepository.findByIdAndSchedule(commentId, schedule);

        if(optionalComment.isPresent()){
            if (userId.equals(optionalComment.get().getUserId())){
                return optionalComment.get();
            } else {
              throw new NotFoundException("사용자가 일치하지 않습니다.");
            }
        }else{
            throw new NotFoundException("선택한 일정에 해당 댓글이 존재하지 않습니다.");
        }
    }

}
