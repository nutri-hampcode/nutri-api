package com.hampcode.repository;

import com.hampcode.model.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
}
