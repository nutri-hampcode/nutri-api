package com.hampcode.controller;

import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.service.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionalPlans")
public class NutritionalPlanController {

    @Autowired
    private NutritionalPlanService nutritionalPlanService;

    @GetMapping("/")
    public ResponseEntity<List<NutritionalPlan>> getAllNutritionalPlans() {
        List<NutritionalPlan> plans = nutritionalPlanService.findAllNutritionalPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionalPlan> getNutritionalPlanById(@PathVariable Integer id) {
        NutritionalPlan plan = nutritionalPlanService.findNutritionalPlanById(id);
        return ResponseEntity.ok(plan);
    }
}
