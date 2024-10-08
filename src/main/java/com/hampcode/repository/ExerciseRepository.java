package com.hampcode.repository;

import com.hampcode.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findByGoalId(Integer goalId);
}
