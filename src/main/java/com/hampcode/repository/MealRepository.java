package com.hampcode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hampcode.model.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    @Query("SELECT m from Meal m where m.dietType.id = :id_diettype ")
    Optional<List<Meal>> findByDietType(@Param("id_diettype") Integer id_diettype);

    Optional<Meal> findMealByNameAndDietTypeId(String meal_name, Integer dietTypeId);
}
