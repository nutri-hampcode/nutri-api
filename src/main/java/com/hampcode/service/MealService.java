package com.hampcode.service; // Asegúrate de que el paquete sea el correcto

import com.hampcode.model.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    // Método para crear una nueva comida usando MealDTO
    public MealDTO createMeal(MealDTO mealDto) {
        Meal meal = new Meal(); // Convertir DTO a entidad
        meal.setName(mealDto.getName());
        meal.setDescription(mealDto.getDescription());
        Meal savedMeal = mealRepository.save(meal);
        return convertToDTO(savedMeal); // Convertir entidad guardada a DTO
    }

    // Método para obtener todas las comidas
    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    // Método para obtener una comida por ID
    public Meal findMealById(Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encuentra la comida"));
    }

    // Método privado para convertir una entidad Meal a MealDTO
    private MealDTO convertToDTO(Meal meal) {
        MealDTO dto = new MealDTO();
        dto.setName(meal.getName());
        dto.setDescription(meal.getDescription());
        return dto;
    }
}