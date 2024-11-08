package com.hampcode.repository;

import com.hampcode.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByUserId(Integer userId);

    Page<Feedback> findByUserId(Integer userId, Pageable pageable);
}