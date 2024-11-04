package com.hampcode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.MealsNutritionalPlan;

public interface MealsNutritionalPlanRepository extends JpaRepository<MealsNutritionalPlan, Integer> {
    List<MealsNutritionalPlan> findByNutritionalPlanId(Integer nutritionalPlanId);
    List<MealsNutritionalPlan> findAllByNutritionalPlanId(Integer id);
}