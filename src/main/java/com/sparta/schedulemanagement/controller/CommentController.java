package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.jwt.JwtUtil;
import com.sparta.schedulemanagement.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/{scheduleId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto createComment(@PathVariable Long scheduleId, @RequestBody @Valid CommentRequestDto requestDto, @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER, required = false) String tokenValue) {
        return commentService.createComment(scheduleId, requestDto, tokenValue);
    }

    @PutMapping("/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody @Valid CommentRequestDto requestDto, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return commentService.updateComment(scheduleId, commentId, requestDto, tokenValue);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestParam String userId, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return commentService.deleteComment(scheduleId, commentId, userId, tokenValue);
    }

}
