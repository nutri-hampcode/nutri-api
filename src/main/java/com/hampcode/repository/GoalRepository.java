package com.hampcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

}
