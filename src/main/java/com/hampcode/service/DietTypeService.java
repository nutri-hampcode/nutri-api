package com.hampcode.service;

import com.hampcode.model.entity.DietType;

import java.util.List;

public interface DietTypeService {
    List<DietType> findAll();
    DietType getOne(Integer id);
    DietType create(DietType dietType);
    DietType update(Integer id, DietType dietType);
    void delete(Integer id);
}
