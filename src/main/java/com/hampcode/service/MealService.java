package com.hampcode.service;

import java.util.List;

import com.hampcode.dto.MealCUDTO;
import com.hampcode.dto.MealDetailsDTO;

public interface MealService {
    List<MealDetailsDTO> findAll();
    List<MealDetailsDTO> findMealPerDietType(Integer id_diettype);
    MealDetailsDTO findById(Integer id);
    MealDetailsDTO create(Integer id_diettype, MealCUDTO mealCDTO);
    MealDetailsDTO update(Integer id, MealCUDTO mealUDTO);
    void delete(Integer id);
}
