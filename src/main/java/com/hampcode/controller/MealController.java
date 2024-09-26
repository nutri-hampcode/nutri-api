package com.hampcode.api; // Ajusta el paquete según tu estructura

import com.hampcode.model.dto.MealDTO; // Importa el DTO
import com.hampcode.model.entity.Meal;
import com.hampcode.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    // Obtener todos los alimentos
    @GetMapping("/")
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meals = mealService.findAllMeals();
        return ResponseEntity.ok(meals);
    }

    // Obtener un alimento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Integer id) {
        Meal meal = mealService.findMealById(id);
        return ResponseEntity.ok(meal);
    }

    // Crear un nuevo alimento utilizando MealDTO
    @PostMapping("/")
    public ResponseEntity<MealDTO> createMeal(@Valid @RequestBody MealDTO mealDto) {
        MealDTO savedMeal = mealService.createMeal(mealDto);
        return ResponseEntity.ok(savedMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer id, @RequestBody Meal mealDetails) {
        Meal existingMeal = mealService.findMealById(id);
        if (existingMeal == null) {
            return ResponseEntity.notFound().build();
        }
        // Supone que existen métodos setters para actualizar los campos deseados
        existingMeal.setName(mealDetails.getName());
        existingMeal.setDescription(mealDetails.getDescription());
        existingMeal.setProteins(mealDetails.getProteins());
        existingMeal.setCarbs(mealDetails.getCarbs());
        existingMeal.setFat(mealDetails.getFat());
        Meal updatedMeal = mealService.saveMeal(existingMeal);
        return ResponseEntity.ok(updatedMeal);
    }

    // Eliminar un alimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Integer id) {
        Meal meal = mealService.findMealById(id);
        if (meal == null) {
            return ResponseEntity.notFound().build();
        }
        mealService.deleteMeal(id);
        return ResponseEntity.ok().build();
    }
}
