package com.hampcode.repository;


import com.hampcode.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
HistoryRepository extends JpaRepository<History, Integer> {
}