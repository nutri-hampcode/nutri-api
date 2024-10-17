package com.hampcode.service;

import com.hampcode.dto.AvailabilityCreateUpdateDTO;
import com.hampcode.dto.DietHistoryCUDTO;
import com.hampcode.dto.DietHistoryDetailsDTO;
import com.hampcode.model.entity.DietHistory;
import com.hampcode.model.entity.Exercise;
import com.hampcode.model.entity.InterPlanEx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DietHistoryService {
    List<DietHistoryDetailsDTO> findAll();
    Page<DietHistoryDetailsDTO> paginate(Integer id_user, Pageable pageable);
    DietHistoryDetailsDTO findById(Integer id);
    DietHistoryDetailsDTO create(Integer id_user, Integer id_meal, DietHistoryCUDTO dietCreateDTO);
    DietHistoryDetailsDTO update(Integer id,Integer id_user,Integer id_meal, DietHistoryCUDTO dietUpdateDTO);
    void delete(Integer id);
}
