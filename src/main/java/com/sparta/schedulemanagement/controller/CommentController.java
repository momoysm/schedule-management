package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/createComment/{scheduleId}")
    public CommentResponseDto createComment(@PathVariable Long scheduleId, @RequestBody @Valid CommentRequestDto requestDto) {
        return commentService.createComment(scheduleId, requestDto);
    }

    @PutMapping("/updateComment/{scheduleId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody @Valid CommentRequestDto requestDto) {
        return commentService.updateComment(scheduleId, commentId, requestDto);
    }

}
