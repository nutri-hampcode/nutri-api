package com.hampcode.api;

import java.util.List;
import java.util.stream.Collectors;

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

            // Check if meal is present
            if (plan.getMeal() != null) {
                MealDetailsDTO mealDetailsDTO = new MealDetailsDTO();
                mealDetailsDTO.setName(plan.getMeal().getName());
                mealDetailsDTO.setDescription(plan.getMeal().getDescription());
                mealDetailsDTO.setCalories(plan.getMeal().getCalories());
                mealDetailsDTO.setImage(plan.getMeal().getImage());
                mealDetailsDTO.setProteins(plan.getMeal().getProteins());
                mealDetailsDTO.setCarbs(plan.getMeal().getCarbs());
                mealDetailsDTO.setFat(plan.getMeal().getFat());
                if (plan.getMeal().getDietType() != null) {
                    mealDetailsDTO.setDietType(plan.getMeal().getDietType().getType());
                }
                dto.setMeal(mealDetailsDTO); // Assign the MealDetailsDTO
            } else {
                // Handle the case where the meal is null
                System.out.println("No meal found for plan: " + plan.getId());
                dto.setMeal(null); // Explicitly set to null, or handle as needed
            }

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
        // Check if meal is present
        if (plan.getMeal() != null) {
            MealDetailsDTO mealDetailsDTO = new MealDetailsDTO();
            mealDetailsDTO.setName(plan.getMeal().getName());
            mealDetailsDTO.setDescription(plan.getMeal().getDescription());
            mealDetailsDTO.setCalories(plan.getMeal().getCalories());
            mealDetailsDTO.setImage(plan.getMeal().getImage());
            mealDetailsDTO.setProteins(plan.getMeal().getProteins());
            mealDetailsDTO.setCarbs(plan.getMeal().getCarbs());
            mealDetailsDTO.setFat(plan.getMeal().getFat());
            if (plan.getMeal().getDietType() != null) {
                mealDetailsDTO.setDietType(plan.getMeal().getDietType().getType());
            }
            dto.setMeal(mealDetailsDTO); // Assign the MealDetailsDTO
        } else {
            // Handle the case where the meal is null
            System.out.println("No meal found for plan: " + plan.getId());
            dto.setMeal(null); // Explicitly set to null, or handle as needed
        }

        dto.setNutritionalPlan(plan.getNutritionalPlan().getType() + " ("
                + plan.getNutritionalPlan().getDoctor().getFirstName()
                + " " + plan.getNutritionalPlan().getDoctor().getLastName() + ")");
        dto.setMealImg(plan.getMeal().getImage());
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

    // New endpoint to retrieve all meals nutritional plans by nutritional plan ID
    @GetMapping("/nutritional-plan/{id}/all")
    public ResponseEntity<List<MealsNutritionalPlanDetailsDTO>> getAllMealsNutritionalPlansByNutritionalPlanId(@PathVariable Integer id) {
        List<MealsNutritionalPlan> mealsNutritionalPlans = mealsNutritionalPlanService.findAllByNutritionalPlanId(id);

        if (mealsNutritionalPlans.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no plans found
        }

        List<MealsNutritionalPlanDetailsDTO> dtoList = mealsNutritionalPlans.stream().map(plan -> {
            MealsNutritionalPlanDetailsDTO dto = new MealsNutritionalPlanDetailsDTO();
            dto.setId(plan.getId());
            dto.setWeekDay(plan.getWeekDay());
            dto.setMealType(plan.getMealType());

            // Set nutritional plan details
            dto.setNutritionalPlan(plan.getNutritionalPlan().getType() + " (" 
                    + plan.getNutritionalPlan().getDoctor().getFirstName() 
                    + " " + plan.getNutritionalPlan().getDoctor().getLastName() + ")");

            // Check if meal is present
            if (plan.getMeal() != null) {
                MealDetailsDTO mealDetailsDTO = new MealDetailsDTO();
                mealDetailsDTO.setId(plan.getMeal().getId());
                mealDetailsDTO.setName(plan.getMeal().getName());
                mealDetailsDTO.setImage(plan.getMeal().getImage());
                mealDetailsDTO.setDescription(plan.getMeal().getDescription());
                mealDetailsDTO.setCalories(plan.getMeal().getCalories());
                mealDetailsDTO.setProteins(plan.getMeal().getProteins());
                mealDetailsDTO.setCarbs(plan.getMeal().getCarbs());
                mealDetailsDTO.setFat(plan.getMeal().getFat());
                if (plan.getMeal().getDietType() != null) {
                    mealDetailsDTO.setDietType(plan.getMeal().getDietType().getType());
                }
                dto.setMeal(mealDetailsDTO); // Assign the MealDetailsDTO
            } else {
                System.out.println("No meal found for plan: " + plan.getId());
                dto.setMeal(null);
            }

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
    
    @GetMapping("/meal/{id}")
    public ResponseEntity<MealDetailsDTO> getMealById(@PathVariable Integer id) {
        Meal meal = mealsNutritionalPlanService.findMealById(id);

        MealDetailsDTO mealDTO = new MealDetailsDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setName(meal.getName());
        mealDTO.setDescription(meal.getDescription());
        mealDTO.setCalories(meal.getCalories());
        mealDTO.setImage(meal.getImage());
        mealDTO.setProteins(meal.getProteins());
        mealDTO.setCarbs(meal.getCarbs());
        mealDTO.setFat(meal.getFat());
        if (meal.getDietType() != null) {
            mealDTO.setDietType(meal.getDietType().getType());
        }

        return ResponseEntity.ok(mealDTO);
    }
}