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

    // Actualizar un alimento existente utilizando MealDTO
    @PutMapping("/{id}")
    public ResponseEntity<MealDTO> updateMeal(@PathVariable Integer id, @Valid @RequestBody MealDTO mealDto) {
        Meal updatedMeal = mealService.updateMeal(id, mealDto); // Usa el método del servicio
        return ResponseEntity.ok(mealService.convertToDTO(updatedMeal)); // Convierte a DTO para la respuesta
    }

    // Eliminar un alimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Integer id) {
        mealService.deleteMeal(id); // Maneja la excepción en el servicio
        return ResponseEntity.noContent().build(); // Devuelve un 204 sin contenido
    }
}
