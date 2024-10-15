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

import com.hampcode.model.entity.Exercise;
import com.hampcode.service.AdminExerciseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/exercises")
public class AdminExerciseController {

    private final AdminExerciseService adminExerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> listExercises() {
        return ResponseEntity.ok(adminExerciseService.getAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Exercise>> paginateExercises(
            @PageableDefault(size = 5, sort = "id") Pageable pageable){
        Page<Exercise> exercises = adminExerciseService.paginate(pageable);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Integer id) {
        Exercise exercise = adminExerciseService.findById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = adminExerciseService.create(exercise);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable("id") Integer id,
                                                   @RequestBody Exercise exercise) {
        Exercise updatedExercise = adminExerciseService.update(id, exercise);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable("id") Integer id) {
        adminExerciseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}