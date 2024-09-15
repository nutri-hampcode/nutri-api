package com.hampcode.repository;

import com.hampcode.model.entity.DietType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietTypeRepository extends JpaRepository<DietType, Integer> {
}
