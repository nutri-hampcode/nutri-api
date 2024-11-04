package com.hampcode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hampcode.model.entity.NutritionalPlan;

public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Integer> {

    @Query("SELECT np FROM NutritionalPlan np WHERE np.user.id = :userId")
    List<NutritionalPlan> findAllByUserId(@Param("userId") Integer userId);
}
