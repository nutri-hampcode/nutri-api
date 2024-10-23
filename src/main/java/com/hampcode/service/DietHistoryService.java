package com.hampcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hampcode.dto.DietHistoryCUDTO;
import com.hampcode.dto.DietHistoryDetailsDTO;

public interface DietHistoryService {
    List<DietHistoryDetailsDTO> findAll();
    Page<DietHistoryDetailsDTO> paginate(Integer id_user, Pageable pageable);
    DietHistoryDetailsDTO findById(Integer id);
    DietHistoryDetailsDTO create(Integer id_user, Integer id_meal, DietHistoryCUDTO dietCreateDTO);
    DietHistoryDetailsDTO update(Integer id,Integer id_user,Integer id_meal, DietHistoryCUDTO dietUpdateDTO);
    void delete(Integer id);
}
