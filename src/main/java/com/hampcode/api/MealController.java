package com.hampcode.api;

import com.hampcode.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("El endpoint está funcionando");
    }

    // Obtener todos los alimentos
    @GetMapping
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
    @PostMapping
    public ResponseEntity<Meal> createMeal(@Valid @RequestBody MealDTO mealDto) {
        System.out.println("Creando nuevo alimento: " + mealDto.getName()); // Log del nombre del alimento
        Meal savedMeal = mealService.createMeal(mealDto);
        System.out.println("Alimento creado con éxito: ID " + savedMeal.getId()); // Log del ID del alimento creado
        return ResponseEntity.status(201).body(savedMeal);
    }

    // Actualizar un alimento existente utilizando MealDTO
    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer id, @Valid @RequestBody MealDTO mealDto) {
        System.out.println("Actualizando alimento ID " + id + ": " + mealDto.getName());
        Meal updatedMeal = mealService.updateMeal(id, mealDto);
        System.out.println("Alimento actualizado: ID " + updatedMeal.getId());
        return ResponseEntity.ok(updatedMeal);
    }

    // Eliminar un alimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Integer id) {
        System.out.println("Eliminando alimento ID " + id);
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}
