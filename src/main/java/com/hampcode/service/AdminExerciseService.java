package com.hampcode.service;

import com.hampcode.model.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminExerciseService {
    List<Exercise> getAll();
    Page<Exercise> paginate(Pageable pageable);
    Exercise findById(Integer id);
    Exercise create(Exercise exercise);
    Exercise update(Integer id, Exercise updateExercise);
    void delete(Integer id);
}
