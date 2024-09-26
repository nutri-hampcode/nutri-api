package com.hampcode.service;

import com.hampcode.model.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public MealDTO createMeal(MealDTO mealDto) {
        Meal meal = new Meal(); // Convertir DTO a entidad
        meal.setName(mealDto.getName());
        meal.setDescription(mealDto.getDescription());
        Meal savedMeal = mealRepository.save(meal);
        return convertToDTO(savedMeal); // Convertir entidad guardada a DTO
    }

    private MealDTO convertToDTO(Meal meal) {
        MealDTO dto = new MealDTO();
        dto.setName(meal.getName());
        dto.setDescription(meal.getDescription());
        return dto;
    }
}
