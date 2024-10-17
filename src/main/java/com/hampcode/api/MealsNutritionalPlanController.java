package com.hampcode.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.dto.MealsNutritionalPlanDTO;
import com.hampcode.model.entity.MealsNutritionalPlan;
import com.hampcode.service.MealsNutritionalPlanService;

@RestController
@RequestMapping("/meals_nutritional_plans")
public class MealsNutritionalPlanController {

    @Autowired
    private MealsNutritionalPlanService mealsNutritionalPlanService;

    @GetMapping
    public ResponseEntity<List<MealsNutritionalPlan>> getAllMealsNutritionalPlans() {
        List<MealsNutritionalPlan> plans = mealsNutritionalPlanService.findAllMealsNutritionalPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealsNutritionalPlan> getMealsNutritionalPlanById(@PathVariable Integer id) {
        MealsNutritionalPlan plan = mealsNutritionalPlanService.findMealsNutritionalPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @PostMapping
    public ResponseEntity<MealsNutritionalPlan> createMealsNutritionalPlan(@Valid @RequestBody MealsNutritionalPlanDTO planDto) {
        MealsNutritionalPlan savedPlan = mealsNutritionalPlanService.createMealsNutritionalPlan(planDto);
        return ResponseEntity.status(201).body(savedPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealsNutritionalPlan> updateMealsNutritionalPlan(@PathVariable Integer id, @Valid @RequestBody MealsNutritionalPlanDTO planDto) {
        MealsNutritionalPlan updatedPlan = mealsNutritionalPlanService.updateMealsNutritionalPlan(id, planDto);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealsNutritionalPlan(@PathVariable Integer id) {
        mealsNutritionalPlanService.deleteMealsNutritionalPlan(id);
        return ResponseEntity.noContent().build();
    }
}