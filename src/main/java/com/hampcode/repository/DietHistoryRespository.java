package com.hampcode.repository;

import com.hampcode.model.entity.DietHistory;
import com.hampcode.model.enums.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface DietHistoryRespository extends JpaRepository<DietHistory, Integer> {
    @Query("SELECT d FROM DietHistory d WHERE d.user.id = :userId")
    Page<DietHistory> findByUserId(Integer userId, Pageable pageable);

    @Query("SELECT m from DietHistory m where m.mealType = :mealType")
    Optional<DietHistory> findByMealType(MealType mealType);

    @Query("SELECT dh FROM DietHistory dh WHERE dh.user.id = :userId AND dh.mealType = :mealType AND dh.date = :date")
    Optional<DietHistory> findByUserIdMealTypeAndDate(@Param("userId") Integer userId, @Param("mealType") MealType mealType, @Param("date") LocalDate date);

}
