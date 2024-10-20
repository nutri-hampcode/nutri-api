package com.hampcode.repository;

import com.hampcode.model.entity.DietType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietTypeRepository extends JpaRepository<DietType, Integer> {
    Optional<DietType> findDietTypeByType(String type);
}
