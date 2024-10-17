package com.hampcode.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.dto.MealDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.service.MealService;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    // Endpoint de prueba
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
        
        // Llamar al servicio para crear la comida y recibir un objeto Meal
        Meal savedMeal = mealService.createMeal(mealDto);
        
        return ResponseEntity.status(201).body(savedMeal); // Devolver la entidad Meal creada
    }

    // Actualizar un alimento existente utilizando MealDTO
    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer id, @Valid @RequestBody MealDTO mealDto) {
        System.out.println("Actualizando alimento ID " + id + ": " + mealDto.getName());
        Meal updatedMeal = mealService.updateMeal(id, mealDto);
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
