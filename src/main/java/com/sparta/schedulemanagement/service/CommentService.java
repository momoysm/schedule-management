package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.exception.NotFoundException;
import com.sparta.schedulemanagement.jwt.JwtUtil;
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
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, User user) {

        Schedule schedule = scheduleService.findScheduleByIdAndUser(scheduleId, user);

        Comment comment = commentRepository.save(new Comment(requestDto, schedule, user));

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto, User user) {

        Schedule schedule = scheduleService.findScheduleByIdAndUser(scheduleId, user);

        Comment comment = existComment(schedule, commentId, user); // 존재하는 댓글인지 확인
        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long scheduleId, Long commentId, User user) {

        Schedule schedule = scheduleService.findScheduleByIdAndUser(scheduleId, user);

        Comment comment = existComment(schedule, commentId, user);
        commentRepository.delete(comment);

        return new ResponseEntity<>("삭제 성공!, HttpStatus코드 : " +  HttpStatus.OK, HttpStatus.OK);
    }

    public Comment existComment(Schedule schedule, Long commentId, User user) {
        Optional<Comment> optionalComment = commentRepository.findByIdAndUserAndSchedule(commentId, user, schedule);

        if(optionalComment.isPresent()){
            if (user.getId().equals(optionalComment.get().getUser().getId())){
                return optionalComment.get();
            } else {
              throw new NotFoundException("사용자가 일치하지 않습니다.");
            }
        }else{
            throw new NotFoundException("선택한 일정에 해당 댓글이 존재하지 않습니다.");
        }
    }

}
