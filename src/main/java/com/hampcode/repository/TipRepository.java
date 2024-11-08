package com.hampcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.Tip;

public interface TipRepository extends JpaRepository<Tip, Integer> {
}
