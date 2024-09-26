package com.hampcode.service.impl;

import com.hampcode.model.entity.History;
import com.hampcode.repository.HistoryRepository;
import com.hampcode.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public History getOne(Integer id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("History not found with id: " + id));
    }

    @Override
    @Transactional
    public History create(History history) {
        return historyRepository.save(history);
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        historyRepository.deleteById(id);
    }
}