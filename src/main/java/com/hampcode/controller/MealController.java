package com.hampcode.controller;

import com.hampcode.model.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;
    @GetMapping("/")
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meals = mealService.findAllMeals();
        return ResponseEntity.ok(meals);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Integer id) {
        Meal meal = mealService.findMealById(id);
        return ResponseEntity.ok(meal);
    }
    @PostMapping
    public ResponseEntity<MealDTO> createMeal(@Valid @RequestBody MealDTO mealDto) {
        MealDTO savedMeal = mealService.createMeal(mealDto);
        return ResponseEntity.ok(savedMeal);
    }
}
