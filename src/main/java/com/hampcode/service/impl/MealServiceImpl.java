package com.hampcode.service.impl;

import com.hampcode.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.repository.MealRepository;
import com.hampcode.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    // Método para crear una nueva comida usando MealDTO
    @Override
    public Meal createMeal(MealDTO mealDto) {
        Meal meal = new Meal(); // Convertir DTO a entidad
        meal.setName(mealDto.getName());
        meal.setDescription(mealDto.getDescription());
        meal.setCalories(mealDto.getCalories()); // Agregado
        meal.setProteins(mealDto.getProteins()); // Agregado
        meal.setCarbs(mealDto.getCarbs()); // Agregado
        meal.setFat(mealDto.getFat()); // Agregado
        meal.setDietType(mealDto.getDietType()); // Agregado

        Meal savedMeal = mealRepository.save(meal);
        return savedMeal;
    }

    // Método para obtener todas las comidas
    @Override
    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    // Método para obtener una comida por ID
    @Override
    public Meal findMealById(Integer id) {
        return mealRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comida no encontrada con ID: " + id));
    }

    // Método para actualizar una comida existente usando MealDTO
    @Override
    public Meal updateMeal(Integer id, MealDTO mealDto) {
        Meal existingMeal = findMealById(id); // Llama al método para obtener la comida

        // Actualiza los campos de la comida existente
        existingMeal.setName(mealDto.getName());
        existingMeal.setDescription(mealDto.getDescription());
        existingMeal.setCalories(mealDto.getCalories()); // Agregado
        existingMeal.setProteins(mealDto.getProteins()); // Agregado
        existingMeal.setCarbs(mealDto.getCarbs()); // Agregado
        existingMeal.setFat(mealDto.getFat()); // Agregado
        existingMeal.setDietType(mealDto.getDietType()); // Agregado

        return mealRepository.save(existingMeal); // Guarda la comida actualizada
    }

    // Método para eliminar una comida
    @Override
    public void deleteMeal(Integer id) {
        Meal existingMeal = findMealById(id); // Verifica si la comida existe
        mealRepository.delete(existingMeal); // Elimina la comida
    }
//COMENTARIO PARA VERIFICAR QUE SI CAMBIA LOLOLL
    // Método privado para convertir una entidad Meal a MealDTO (opcional)
    private MealDTO convertToDTO(Meal meal) {
        MealDTO dto = new MealDTO();
        dto.setName(meal.getName());
        dto.setDescription(meal.getDescription());
        dto.setCalories(meal.getCalories()); // Agregado
        dto.setProteins(meal.getProteins()); // Agregado
        dto.setCarbs(meal.getCarbs()); // Agregado
        dto.setFat(meal.getFat()); // Agregado
        dto.setDietType(meal.getDietType()); // Agregado
        return dto;
    }
}