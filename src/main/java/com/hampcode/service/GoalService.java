package com.hampcode.service;

import com.hampcode.model.entity.Goal;

import java.util.List;

public interface GoalService {
    List<Goal> findAll();
    Goal getOne(Integer id);
    Goal create(Goal goal);
    Goal update(Integer id,Goal goal);
}
