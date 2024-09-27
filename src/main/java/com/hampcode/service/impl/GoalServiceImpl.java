package com.hampcode.service.impl;

import com.hampcode.model.entity.Goal;
import com.hampcode.repository.GoalRepository;
import com.hampcode.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Goal getOne(Integer id) {
        return goalRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Goal not found with id: " + id));
    }

    @Transactional
    @Override
    public Goal create(Goal goal) {
        return goalRepository.save(goal);
    }

    @Transactional
    @Override
    public Goal update(Integer id, Goal goal) {
        Goal doc = goalRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Goal not found with id: " + id));
        doc.setName(goal.getName());
        return goalRepository.save(doc);
    }

}
