package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "댓글을 입력하세요.")
    private String comment;

    @NotBlank(message = "사용자 아이디를 입력하세요.")
    private String userId;

}
