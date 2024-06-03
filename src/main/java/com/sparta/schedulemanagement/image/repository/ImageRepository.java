package com.sparta.schedulemanagement.image.repository;

import com.sparta.schedulemanagement.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
