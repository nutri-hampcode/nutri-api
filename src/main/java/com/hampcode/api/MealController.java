package com.hampcode.api;

import com.hampcode.dto.MealCUDTO;
import com.hampcode.dto.MealDetailsDTO;
import com.hampcode.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/meal")
public class MealController {
    private final MealService mealService;

    @GetMapping
    public ResponseEntity<List<MealDetailsDTO>> findAll() {
        List<MealDetailsDTO> meal = mealService.findAll();
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDetailsDTO> findById(@PathVariable Integer id) {
        MealDetailsDTO meal = mealService.findById(id);
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }

    @GetMapping("/diet_type/{id_diet_type}")
    public ResponseEntity<List<MealDetailsDTO>> findMealDietType(@PathVariable Integer id_diet_type) {
        List<MealDetailsDTO> meal = mealService.findMealPerDietType(id_diet_type);
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }

    @PostMapping("/{id_diet_type}")
    public ResponseEntity<MealDetailsDTO> create(@PathVariable Integer id_diet_type, @Valid @RequestBody MealCUDTO mealCDTO) {
        MealDetailsDTO m = mealService.create(id_diet_type, mealCDTO);
        return new ResponseEntity<>(m, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealDetailsDTO> update(@PathVariable Integer id,@Valid @RequestBody MealCUDTO mealUDTO) {
        MealDetailsDTO aux = mealService.update(id, mealUDTO);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        mealService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
