package com.hampcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.NutritionalPlan;

public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Integer> {
}