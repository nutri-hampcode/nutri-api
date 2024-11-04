package com.hampcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.dto.MealsNutritionalPlanDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Meal;
import com.hampcode.model.entity.MealsNutritionalPlan;
import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.repository.MealRepository;
import com.hampcode.repository.MealsNutritionalPlanRepository;
import com.hampcode.repository.NutritionalPlanRepository;
import com.hampcode.service.MealsNutritionalPlanService;

@Service
public class MealsNutritionalPlanServiceImpl implements MealsNutritionalPlanService {

    @Autowired
    private MealsNutritionalPlanRepository mealsNutritionalPlanRepository;

    @Autowired
    private NutritionalPlanRepository nutritionalPlanRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public MealsNutritionalPlan createMealsNutritionalPlan(MealsNutritionalPlanDTO planDto) {
        MealsNutritionalPlan plan = new MealsNutritionalPlan();
        plan.setWeekDay(planDto.getWeekDay());
        plan.setMealType(planDto.getMealType());

        NutritionalPlan nutritionalPlan = nutritionalPlanRepository.findById(planDto.getNutritionalPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutritional Plan not found with ID: " + planDto.getNutritionalPlanId()));
        plan.setNutritionalPlan(nutritionalPlan);

        Meal meal = mealRepository.findById(planDto.getMealId())
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with ID: " + planDto.getMealId()));
        plan.setMeal(meal);

        return mealsNutritionalPlanRepository.save(plan);
    }

    @Override
    public List<MealsNutritionalPlan> findAllMealsNutritionalPlans() {
        return mealsNutritionalPlanRepository.findAll();
    }

    @Override
    public MealsNutritionalPlan findMealsNutritionalPlanById(Integer id) {
        return mealsNutritionalPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meals Nutritional Plan not found with ID: " + id));
    }

    @Override
    public MealsNutritionalPlan updateMealsNutritionalPlan(Integer id, MealsNutritionalPlanDTO planDto) {
        MealsNutritionalPlan existingPlan = findMealsNutritionalPlanById(id);
        existingPlan.setWeekDay(planDto.getWeekDay());
        existingPlan.setMealType(planDto.getMealType());

        NutritionalPlan nutritionalPlan = nutritionalPlanRepository.findById(planDto.getNutritionalPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutritional Plan not found with ID: " + planDto.getNutritionalPlanId()));
        existingPlan.setNutritionalPlan(nutritionalPlan);

        Meal meal = mealRepository.findById(planDto.getMealId())
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with ID: " + planDto.getMealId()));
        existingPlan.setMeal(meal);

        return mealsNutritionalPlanRepository.save(existingPlan);
    }

    @Override
    public void deleteMealsNutritionalPlan(Integer id) {
        MealsNutritionalPlan existingPlan = findMealsNutritionalPlanById(id);
        mealsNutritionalPlanRepository.delete(existingPlan);
    }

    @Override
    public List<Meal> findMealsByNutritionalPlanId(Integer nutritionalPlanId) {
        return mealsNutritionalPlanRepository.findByNutritionalPlanId(nutritionalPlanId)
                    .stream()
                    .map(MealsNutritionalPlan::getMeal)
                    .toList();
    }

    @Override
    public List<MealsNutritionalPlan> findAllByNutritionalPlanId(Integer id) {
        return mealsNutritionalPlanRepository.findAllByNutritionalPlanId(id);
    }

    @Override
    public Meal findMealById(Integer id) {
        return mealRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Meal not found with ID: " + id));
    }

}