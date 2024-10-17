package com.hampcode.service;

<<<<<<< HEAD
import com.hampcode.dto.MealDTO;
import com.hampcode.model.entity.Meal;
=======
import com.hampcode.dto.MealCUDTO;
import com.hampcode.dto.MealDetailsDTO;
>>>>>>> origin/feature/crud-diethistory

import java.util.List;

public interface MealService {
<<<<<<< HEAD
    Meal createMeal(MealDTO mealDto);
    List<Meal> findAllMeals();
    Meal findMealById(Integer id);
    Meal updateMeal(Integer id, MealDTO mealDto);
    void deleteMeal(Integer id);
}
=======
    List<MealDetailsDTO> findAll();
    List<MealDetailsDTO> findMealPerDietType(Integer id_diettype);
    MealDetailsDTO findById(Integer id);
    MealDetailsDTO create(Integer id_diettype, MealCUDTO mealCDTO);
    MealDetailsDTO update(Integer id, MealCUDTO mealUDTO);
    void delete(Integer id);
}
>>>>>>> origin/feature/crud-diethistory
