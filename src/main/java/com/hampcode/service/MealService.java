package com.hampcode.service; // Asegúrate de que el paquete sea el correcto

import com.hampcode.model.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.exception.ResourceNotFoundException; // Asegúrate de importar la excepción
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
        return mealRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comida no encontrada con ID: " + id));
    }

    // Método para actualizar una comida existente usando MealDTO
    public Meal updateMeal(Integer id, MealDTO mealDto) {
        Meal existingMeal = findMealById(id); // Llama al método para obtener la comida

        // Actualiza los campos de la comida existente
        existingMeal.setName(mealDto.getName());
        existingMeal.setDescription(mealDto.getDescription());
        // Agrega otros campos que quieras actualizar aquí...

        return mealRepository.save(existingMeal); // Guarda la comida actualizada
    }

    // Método para eliminar una comida
    public void deleteMeal(Integer id) {
        Meal existingMeal = findMealById(id); // Verifica si la comida existe
        mealRepository.delete(existingMeal); // Elimina la comida
    }

    // Método privado para convertir una entidad Meal a MealDTO
    private MealDTO convertToDTO(Meal meal) {
        MealDTO dto = new MealDTO();
        dto.setName(meal.getName());
        dto.setDescription(meal.getDescription());
        return dto;
    }
}
