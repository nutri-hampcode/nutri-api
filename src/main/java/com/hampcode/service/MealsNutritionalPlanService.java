package com.hampcode.service;

import java.util.List;

import com.hampcode.dto.MealsNutritionalPlanDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.model.entity.MealsNutritionalPlan;

public interface MealsNutritionalPlanService {
    MealsNutritionalPlan createMealsNutritionalPlan(MealsNutritionalPlanDTO planDto);
    List<MealsNutritionalPlan> findAllMealsNutritionalPlans();
    MealsNutritionalPlan findMealsNutritionalPlanById(Integer id);
    MealsNutritionalPlan updateMealsNutritionalPlan(Integer id, MealsNutritionalPlanDTO planDto);
    void deleteMealsNutritionalPlan(Integer id);
    List<MealsNutritionalPlan> findAllByNutritionalPlanId(Integer id);

    public List<Meal> findMealsByNutritionalPlanId(Integer id);
    Meal findMealById(Integer id);
}