package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
