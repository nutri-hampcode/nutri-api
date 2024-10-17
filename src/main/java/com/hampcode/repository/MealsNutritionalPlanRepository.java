package com.hampcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hampcode.model.entity.MealsNutritionalPlan;

@Repository
public interface MealsNutritionalPlanRepository extends JpaRepository<MealsNutritionalPlan, Integer> {
}
