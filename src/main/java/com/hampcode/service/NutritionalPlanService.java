package com.hampcode.service;

import java.util.List;

import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.model.entity.NutritionalPlan;

public interface NutritionalPlanService {
    NutritionalPlan createNutritionalPlan(NutritionalPlanDTO planDto);
    List<NutritionalPlan> findAllNutritionalPlans();
    NutritionalPlan findNutritionalPlanById(Integer id);
    NutritionalPlan updateNutritionalPlan(Integer id, NutritionalPlanDTO planDto);
    void deleteNutritionalPlan(Integer id);
    List<NutritionalPlan> findNutritionalPlansByUserId(Integer userId);
}
