package com.hampcode.controller;

import com.hampcode.model.entity.Exercise;
import com.hampcode.repository.ExerciseRepository;
import com.hampcode.service.AdminExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminExerciseServiceImpl implements AdminExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Exercise> getAll() {
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
    public Exercise create(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Transactional
    @Override
    public Exercise update(Integer id, Exercise updateExercise) {
        Exercise exercise = findById(id);
        if (updateExercise.getDescription() != null) {
            exercise.setDescription(updateExercise.getDescription());
        }
        if (updateExercise.getTips() != null) {
            exercise.setTips(updateExercise.getTips());
        }
        if (updateExercise.getImage() != null) {
            exercise.setImage(updateExercise.getImage());
        }
        if (updateExercise.getLinkVideo() != null) {
            exercise.setLinkVideo(updateExercise.getLinkVideo());
        }
        return exerciseRepository.save(exercise);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Exercise exercise = findById(id);
        exerciseRepository.delete(exercise);
    }
}
