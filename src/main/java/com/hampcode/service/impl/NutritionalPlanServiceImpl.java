package com.hampcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.repository.NutritionalPlanRepository;
import com.hampcode.service.NutritionalPlanService;

@Service
public class NutritionalPlanServiceImpl implements NutritionalPlanService {

    @Autowired
    private NutritionalPlanRepository nutritionalPlanRepository;

    @Override
    public NutritionalPlan createNutritionalPlan(NutritionalPlanDTO planDto) {
        NutritionalPlan plan = new NutritionalPlan();
        plan.setType(planDto.getType());
        plan.setDoctor(planDto.getDoctor());
        plan.setUser(planDto.getUser());
        return nutritionalPlanRepository.save(plan);
    }

    @Override
    public List<NutritionalPlan> findAllNutritionalPlans() {
        return nutritionalPlanRepository.findAll();
    }

    @Override
    public NutritionalPlan findNutritionalPlanById(Integer id) {
        return nutritionalPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutritional Plan not found with ID: " + id));
    }

    @Override
    public NutritionalPlan updateNutritionalPlan(Integer id, NutritionalPlanDTO planDto) {
        NutritionalPlan existingPlan = findNutritionalPlanById(id);
        existingPlan.setType(planDto.getType());
        existingPlan.setDoctor(planDto.getDoctor());
        existingPlan.setUser(planDto.getUser());
        return nutritionalPlanRepository.save(existingPlan);
    }

    @Override
    public void deleteNutritionalPlan(Integer id) {
        NutritionalPlan existingPlan = findNutritionalPlanById(id);
        nutritionalPlanRepository.delete(existingPlan);
    }
    @Override
    public List<NutritionalPlan> findNutritionalPlansByUserId(Integer userId) {
        return nutritionalPlanRepository.findAllByUserId(userId);
    }
}