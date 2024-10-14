package com.hampcode.api;

import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.service.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/nutritional-plans")
public class NutritionalPlanController {

    @Autowired
    private NutritionalPlanService nutritionalPlanService;

    // Obtener todos los planes nutricionales
    @GetMapping
    public ResponseEntity<List<NutritionalPlan>> getAllPlans() {
        List<NutritionalPlan> plans = nutritionalPlanService.findAllPlans();
        return ResponseEntity.ok(plans);
    }

    // Obtener un plan nutricional por ID
    @GetMapping("/{id}")
    public ResponseEntity<NutritionalPlan> getPlanById(@PathVariable Integer id) {
        NutritionalPlan plan = nutritionalPlanService.findPlanById(id);
        return ResponseEntity.ok(plan);
    }

    // Crear un nuevo plan nutricional
    @PostMapping
    public ResponseEntity<NutritionalPlan> createPlan(@Valid @RequestBody NutritionalPlanDTO planDTO) {
        NutritionalPlan createdPlan = nutritionalPlanService.createNutritionalPlan(planDTO);
        return ResponseEntity.status(201).body(createdPlan);
    }

    // Actualizar un plan nutricional existente
    @PutMapping("/{id}")
    public ResponseEntity<NutritionalPlan> updatePlan(@PathVariable Integer id, @Valid @RequestBody NutritionalPlanDTO planDTO) {
        NutritionalPlan updatedPlan = nutritionalPlanService.updatePlan(id, planDTO);
        return ResponseEntity.ok(updatedPlan);
    }

    // Eliminar un plan nutricional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Integer id) {
        nutritionalPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}