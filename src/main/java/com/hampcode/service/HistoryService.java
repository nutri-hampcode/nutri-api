package com.hampcode.service;

import com.hampcode.model.entity.History;

import java.util.List;

public interface HistoryService {
    List<History> findAll();
    History getOne(Integer id);
    History create(History history);
    History update(Integer id, History history);
    void delete(Integer id);
}
