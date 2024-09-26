package com.hampcode.model.service;

import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.model.repository.NutritionalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionalPlanService {

    @Autowired
    private NutritionalPlanRepository nutritionalPlanRepository;

    public List<NutritionalPlan> findAllNutritionalPlans() {
        return nutritionalPlanRepository.findAll();
    }

    public NutritionalPlan findNutritionalPlanById(Integer id) {
        return nutritionalPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("Nutritional plan not found"));
    }
}
