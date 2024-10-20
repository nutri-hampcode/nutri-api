package com.hampcode.service;

import java.util.List;

import com.hampcode.model.entity.Goal;

public interface GoalService {
    List<Goal> findAll();
    Goal getOne(Integer id);
    Goal create(Goal goal);
    Goal update(Integer id,Goal goal);
}