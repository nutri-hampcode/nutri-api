package com.hampcode.api;

import com.hampcode.dto.ExerciseDTO;
import com.hampcode.dto.TipDTO;
import com.hampcode.model.entity.Exercise;
import com.hampcode.service.ExerciseService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> listExercisesDTO() {
        return ResponseEntity.ok(exerciseService.getAllExercisesDTO());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Exercise>> paginateExercises(
            @PageableDefault(size = 5, sort = "id") Pageable pageable){
        Page<Exercise> exercises = exerciseService.paginate(pageable);
        return new ResponseEntity<Page<Exercise>>(exercises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable("id") Integer id) {
        Exercise exercise = exerciseService.findById(id);
        ExerciseDTO exerciseDTO = exerciseService.convertToDTO(exercise);
        return new ResponseEntity<>(exerciseDTO, HttpStatus.OK);
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
        return new ResponseEntity<ExerciseDTO>(exerciseService.convertToDTO(createdExercise), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable("id") Integer id,
                                                   @RequestBody Exercise exercise) {
        Exercise updatedExercise = exerciseService.update(id, exercise);
        return new ResponseEntity<Exercise>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable("id") Integer id) {
        exerciseService.delete(id);
        return new ResponseEntity<Exercise>(HttpStatus.NO_CONTENT);
    }
}
