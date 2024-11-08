package com.hampcode.service;

import java.util.List;

import com.hampcode.dto.GoalDTO;
import com.hampcode.model.entity.Goal;
import org.springframework.transaction.annotation.Transactional;

public interface GoalService {
    List<Goal> findAll();
    List<GoalDTO> getAllGoalsDTO();
    Goal getOne(Integer id);
    Goal create(Goal goal);
    Goal update(Integer id,Goal goal);
    GoalDTO convertToDTO(Goal goal);
}