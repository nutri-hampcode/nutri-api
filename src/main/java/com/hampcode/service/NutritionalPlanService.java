package com.hampcode.service;

import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.model.entity.NutritionalPlan;

import java.util.List;

public interface NutritionalPlanService {
    NutritionalPlan createNutritionalPlan(NutritionalPlanDTO nutritionalPlanDTO);
    List<NutritionalPlan> findAllPlans();
    NutritionalPlan findPlanById(Integer id);
    NutritionalPlan updatePlan(Integer id, NutritionalPlanDTO nutritionalPlanDTO);
    void deletePlan(Integer id);
    NutritionalPlan convertToEntity(NutritionalPlanDTO nutritionalPlanDTO);
}
