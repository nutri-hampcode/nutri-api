package com.hampcode.service;

import com.hampcode.dto.ExerciseDTO;
import com.hampcode.dto.TipDTO;
import com.hampcode.model.entity.Exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAllExercises();
    Page<Exercise> paginate(Pageable pageable);
    ExerciseDTO convertToDTO(Exercise exercise);
    List<ExerciseDTO> getAllExercisesDTO();
    List<TipDTO> getTipsForExercise(Integer exerciseId);
    List<ExerciseDTO> getExercisesByGoalId(Integer exerciseId);
    Exercise findById(Integer id);
    Exercise create(ExerciseDTO exerciseDTO);
    Exercise update(Integer id, Exercise updateExercise);
    void delete(Integer id);
}
