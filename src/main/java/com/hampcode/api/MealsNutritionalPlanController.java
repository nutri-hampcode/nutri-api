package com.hampcode.api;

import java.util.List;

import com.hampcode.dto.NutritionalPlanDetailsDTO;
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

import com.hampcode.dto.MealDetailsDTO;
import com.hampcode.dto.MealsNutritionalPlanDTO;
import com.hampcode.dto.MealsNutritionalPlanDetailsDTO;
import com.hampcode.model.entity.Meal;
import com.hampcode.model.entity.MealsNutritionalPlan;
import com.hampcode.service.MealsNutritionalPlanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/meals_nutritional_plans")
public class MealsNutritionalPlanController {

    @Autowired
    private MealsNutritionalPlanService mealsNutritionalPlanService;

    @GetMapping
    public ResponseEntity<List<MealsNutritionalPlanDetailsDTO>> getAllMealsNutritionalPlans() {
        List<MealsNutritionalPlan> plans = mealsNutritionalPlanService.findAllMealsNutritionalPlans();

        List<MealsNutritionalPlanDetailsDTO> dtoList = plans.stream().map(plan -> {
            MealsNutritionalPlanDetailsDTO dto = new MealsNutritionalPlanDetailsDTO();
            dto.setWeekDay(plan.getWeekDay());
            dto.setMealType(plan.getMealType());

            dto.setNutritionalPlan(plan.getNutritionalPlan().getType() + " ("
                    + plan.getNutritionalPlan().getDoctor().getFirstName()
                    + " " + plan.getNutritionalPlan().getDoctor().getLastName() + ")");

            dto.setMeal(plan.getMeal().getName() + " (" + plan.getMeal().getDescription() + ")");
            dto.setMealImg(plan.getMeal().getImage());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MealsNutritionalPlanDetailsDTO> getMealsNutritionalPlanById(@PathVariable Integer id) {
        MealsNutritionalPlan plan = mealsNutritionalPlanService.findMealsNutritionalPlanById(id);
        MealsNutritionalPlanDetailsDTO dto = new MealsNutritionalPlanDetailsDTO();
        dto.setWeekDay(plan.getWeekDay());
        dto.setMealType(plan.getMealType());
        dto.setMeal(plan.getMeal().getName() + " (" + plan.getMeal().getDescription() + ")");

        dto.setNutritionalPlan(plan.getNutritionalPlan().getType() + " ("
                + plan.getNutritionalPlan().getDoctor().getFirstName()
                + " " + plan.getNutritionalPlan().getDoctor().getLastName() + ")");

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MealsNutritionalPlan> createMealsNutritionalPlan(@Valid @RequestBody MealsNutritionalPlanDTO planDto) {
        MealsNutritionalPlan savedPlan = mealsNutritionalPlanService.createMealsNutritionalPlan(planDto);
        return ResponseEntity.status(201).body(savedPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealsNutritionalPlan> updateMealsNutritionalPlan(@PathVariable Integer id, @Valid @RequestBody MealsNutritionalPlanDTO planDto) {
        MealsNutritionalPlan updatedPlan = mealsNutritionalPlanService.updateMealsNutritionalPlan(id, planDto);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealsNutritionalPlan(@PathVariable Integer id) {
        mealsNutritionalPlanService.deleteMealsNutritionalPlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nutritional-plan/{id}/meals")
    public ResponseEntity<List<MealDetailsDTO>> getMealsByNutritionalPlanId(@PathVariable Integer id) {
        List<Meal> meals = mealsNutritionalPlanService.findMealsByNutritionalPlanId(id);

        List<MealDetailsDTO> mealDTOs = meals.stream().map(meal -> {
            MealDetailsDTO mealDTO = new MealDetailsDTO();
            mealDTO.setName(meal.getName());
            mealDTO.setDescription(meal.getDescription());
            mealDTO.setCalories(meal.getCalories());
            mealDTO.setProteins(meal.getProteins());
            mealDTO.setCarbs(meal.getCarbs());
            mealDTO.setFat(meal.getFat());
            if (meal.getDietType() != null) {
                mealDTO.setDietType(meal.getDietType().getType());
            }
            return mealDTO;
        }).toList();

        return ResponseEntity.ok(mealDTOs);
    }

}