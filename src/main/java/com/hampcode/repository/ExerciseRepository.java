package com.hampcode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findByGoalId(Integer goalId);
}
