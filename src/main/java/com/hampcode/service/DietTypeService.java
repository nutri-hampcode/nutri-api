package com.hampcode.service;

import com.hampcode.dto.DietTypeDTO;

import java.util.List;

public interface DietTypeService {
    List<DietTypeDTO> findAll();
    DietTypeDTO getOne(Integer id);
    DietTypeDTO create(DietTypeDTO dietTypeDTO);
    DietTypeDTO update(Integer id, DietTypeDTO dietTypeDTO);
    void delete(Integer id);
}
