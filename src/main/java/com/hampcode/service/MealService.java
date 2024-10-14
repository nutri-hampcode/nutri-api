package com.hampcode.service;

import com.hampcode.dto.MealDTO;
import com.hampcode.model.entity.Meal;

import java.util.List;

public interface MealService {
    Meal createMeal(MealDTO mealDto);
    List<Meal> findAllMeals();
    Meal findMealById(Integer id);
    Meal updateMeal(Integer id, MealDTO mealDto);
    void deleteMeal(Integer id);
}