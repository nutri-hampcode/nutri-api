package com.hampcode.repository;

import com.hampcode.model.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepository extends JpaRepository<Tip, Integer> {
}
