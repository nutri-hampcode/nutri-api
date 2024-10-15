package com.hampcode.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.dto.ExerciseDTO;
import com.hampcode.dto.TipDTO;
import com.hampcode.model.entity.Exercise;
import com.hampcode.service.ExerciseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> listExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @GetMapping("/DTO")
    public ResponseEntity<List<ExerciseDTO>> listExercisesDTO() {
        return ResponseEntity.ok(exerciseService.getAllExercisesDTO());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Exercise>> paginateExercises(
            @PageableDefault(size = 5, sort = "id") Pageable pageable){
        Page<Exercise> exercises = exerciseService.paginate(pageable);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Integer id) {
        Exercise exercise = exerciseService.findById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @GetMapping("/goal/{goalId}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByGoalId(@PathVariable Integer goalId) {
        List<ExerciseDTO> exercises = exerciseService.getExercisesByGoalId(goalId);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("{exerciseId}/tips")
    public ResponseEntity<List<TipDTO>> getTipsForExercise(@PathVariable("exerciseId") Integer id){
        List<TipDTO> tips = exerciseService.getTipsForExercise(id);
        return ResponseEntity.ok(tips);
    }

    @PostMapping
    public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseDTO exerciseDTO) {
        Exercise createdExercise = exerciseService.create(exerciseDTO);
        return new ResponseEntity<>(exerciseService.convertToDTO(createdExercise), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable("id") Integer id,
                                                   @RequestBody Exercise exercise) {
        Exercise updatedExercise = exerciseService.update(id, exercise);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable("id") Integer id) {
        exerciseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}