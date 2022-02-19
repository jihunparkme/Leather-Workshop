package com.leather.workshop.domain.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r ORDER BY r.id DESC")
    List<Review> findAllDesc();
}
