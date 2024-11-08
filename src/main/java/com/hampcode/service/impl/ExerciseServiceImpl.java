package com.hampcode.service.impl;

import com.hampcode.dto.ExerciseDTO;
import com.hampcode.dto.GoalDTO;
import com.hampcode.dto.TipDTO;
import com.hampcode.model.entity.Exercise;
import com.hampcode.model.entity.Goal;
import com.hampcode.model.entity.Tip;
import com.hampcode.repository.ExerciseRepository;
import com.hampcode.repository.GoalRepository;
import com.hampcode.repository.TipRepository;
import com.hampcode.service.ExerciseService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final GoalRepository goalRepository;
    private final TipRepository tipRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Exercise> paginate(Pageable pageable) {
        return exerciseRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).orElseThrow(()->new RuntimeException("No exercise found with id: " + id));
    }

    @Transactional
    @Override
    public List<ExerciseDTO> getAllExercisesDTO() {
        return getAllExercises().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TipDTO> getTipsForExercise(Integer exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()-> new RuntimeException("No exercise found with id: " + exerciseId));

        return exercise.getTips().stream()
                .map(tip -> {
                    TipDTO tipDTO = new TipDTO();
                    tipDTO.setId(tip.getId());
                    tipDTO.setContent(tip.getContent());
                    return tipDTO;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExerciseDTO> getExercisesByGoalId(Integer goalId) {
        if (!goalRepository.existsById(goalId)) {
            throw new RuntimeException("Goal not found with id: " + goalId);
        }

        List<Exercise> exercises = exerciseRepository.findByGoalId(goalId);
        return exercises.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public Exercise create(ExerciseDTO exerciseDTO) {
        Exercise exercise = new Exercise();
        exercise.setLinkVideo(exerciseDTO.getLinkVideo());
        exercise.setImage(exerciseDTO.getImage());
        exercise.setDescription(exerciseDTO.getDescription());

        Goal goal =goalRepository.findById(exerciseDTO.getGoal().getId())
                .orElseThrow(()->new RuntimeException("Goal not found"));
        exercise.setGoal(goal);

        exercise = exerciseRepository.save(exercise);

        List<Tip> tips = new ArrayList<>();
        for (TipDTO tipDTO : exerciseDTO.getTips()) {
            Tip tip = new Tip();
            tip.setContent(tipDTO.getContent());
            tip.setExercise(exercise);
            tips.add(tipRepository.save(tip));
        }
        exercise.setTips(tips);
        return exerciseRepository.save(exercise);
    }

    @Transactional
    @Override
    public Exercise update(Integer id, Exercise updateExercise) {
        Exercise exercise = findById(id);
        if (updateExercise.getDescription() != null) {
            exercise.setDescription(updateExercise.getDescription());
        }
        if (updateExercise.getImage() != null) {
            exercise.setImage(updateExercise.getImage());
        }
        if (updateExercise.getLinkVideo() != null) {
            exercise.setLinkVideo(updateExercise.getLinkVideo());
        }
        if (updateExercise.getGoal() != null) {
            exercise.setGoal(updateExercise.getGoal());
        }
        return exerciseRepository.save(exercise);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Exercise exercise = findById(id);
        exerciseRepository.delete(exercise);
    }


    @Transactional
    @Override
    public ExerciseDTO convertToDTO(Exercise exercise) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setLinkVideo(exercise.getLinkVideo());
        exerciseDTO.setImage(exercise.getImage());
        exerciseDTO.setDescription(exercise.getDescription());

        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(exercise.getGoal().getId());
        goalDTO.setGoalType(exercise.getGoal().getGoalType());

        exerciseDTO.setGoal(goalDTO);

        List<TipDTO> tipDTOs = exercise.getTips().stream()
                .map(tip -> {
                    TipDTO tipDTO = new TipDTO();
                    tipDTO.setId(tip.getId());
                    tipDTO.setContent(tip.getContent());
                    return tipDTO;
                })
                .collect(Collectors.toList());

        exerciseDTO.setTips(tipDTOs);

        return exerciseDTO;
    }
}
