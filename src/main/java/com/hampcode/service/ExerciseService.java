package com.hampcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hampcode.dto.ExerciseDTO;
import com.hampcode.dto.TipDTO;
import com.hampcode.model.entity.Exercise;

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
