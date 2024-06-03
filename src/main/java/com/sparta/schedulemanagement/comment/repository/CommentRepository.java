package com.sparta.schedulemanagement.comment.repository;

import com.sparta.schedulemanagement.comment.entity.Comment;
import com.sparta.schedulemanagement.schedule.entity.Schedule;
import com.sparta.schedulemanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUserAndSchedule(Long commentId, User user, Schedule schedule);
}
