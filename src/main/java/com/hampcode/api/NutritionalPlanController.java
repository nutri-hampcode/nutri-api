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

import jakarta.validation.Valid;
import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.service.NutritionalPlanService;

@RestController
@RequestMapping("/nutritional_plans")
public class NutritionalPlanController {

    @Autowired
    private NutritionalPlanService nutritionalPlanService;

    // Endpoint to test
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("The endpoint is working");
    }

    // Get all nutritional plans
    @GetMapping
    public ResponseEntity<List<NutritionalPlan>> getAllNutritionalPlans() {
        List<NutritionalPlan> plans = nutritionalPlanService.findAllNutritionalPlans();
        return ResponseEntity.ok(plans);
    }

    // Get a nutritional plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<NutritionalPlan> getNutritionalPlanById(@PathVariable Integer id) {
        NutritionalPlan plan = nutritionalPlanService.findNutritionalPlanById(id);
        return ResponseEntity.ok(plan);
    }

    // Create a new nutritional plan using NutritionalPlanDTO
    @PostMapping
    public ResponseEntity<NutritionalPlan> createNutritionalPlan(@Valid @RequestBody NutritionalPlanDTO planDto) {
        NutritionalPlan savedPlan = nutritionalPlanService.createNutritionalPlan(planDto);
        return ResponseEntity.status(201).body(savedPlan);
    }

    // Update an existing nutritional plan using NutritionalPlanDTO
    @PutMapping("/{id}")
    public ResponseEntity<NutritionalPlan> updateNutritionalPlan(@PathVariable Integer id, @Valid @RequestBody NutritionalPlanDTO planDto) {
        NutritionalPlan updatedPlan = nutritionalPlanService.updateNutritionalPlan(id, planDto);
        return ResponseEntity.ok(updatedPlan);
    }

    // Delete a nutritional plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutritionalPlan(@PathVariable Integer id) {
        nutritionalPlanService.deleteNutritionalPlan(id);
        return ResponseEntity.noContent().build();
    }
}