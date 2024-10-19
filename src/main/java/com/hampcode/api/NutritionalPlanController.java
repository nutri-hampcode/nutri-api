package com.hampcode.api;

import java.util.List;
import java.util.stream.Collectors;

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

import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.dto.NutritionalPlanDetailsDTO;
import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.service.NutritionalPlanService;

import jakarta.validation.Valid;

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
    @GetMapping("/plans")
    public List<NutritionalPlanDetailsDTO> getAllNutritionalPlans() {
        return nutritionalPlanService.findAllNutritionalPlans()
            .stream()
            .map(plan -> {
                NutritionalPlanDetailsDTO dto = new NutritionalPlanDetailsDTO();
                dto.setType(plan.getType());
                dto.setDoctor(plan.getDoctor().getFirstName() + " (" + plan.getDoctor().getLastName() + ")");
                dto.setUser(plan.getUser().getName() + " (" + plan.getUser().getUsername() + ")");
                return dto;
            })
            .collect(Collectors.toList());
    }

    // Get a nutritional plan by ID
    @GetMapping("/plans/{id}")
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
    @PutMapping("/plans/{id}")
    public ResponseEntity<NutritionalPlan> updateNutritionalPlan(@PathVariable Integer id, @Valid @RequestBody NutritionalPlanDTO planDto) {
        NutritionalPlan updatedPlan = nutritionalPlanService.updateNutritionalPlan(id, planDto);
        return ResponseEntity.ok(updatedPlan);
    }

    // Delete a nutritional plan
    @DeleteMapping("/plans/{id}")
    public ResponseEntity<Void> deleteNutritionalPlan(@PathVariable Integer id) {
        nutritionalPlanService.deleteNutritionalPlan(id);
        return ResponseEntity.noContent().build();
    }
}