package com.hampcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hampcode.model.entity.Exercise;

public interface AdminExerciseService {
    List<Exercise> getAll();
    Page<Exercise> paginate(Pageable pageable);
    Exercise findById(Integer id);
    Exercise create(Exercise exercise);
    Exercise update(Integer id, Exercise updateExercise);
    void delete(Integer id);
}
