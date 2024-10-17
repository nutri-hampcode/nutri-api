package com.hampcode.service.impl;

import com.hampcode.dto.DietHistoryCUDTO;
import com.hampcode.dto.DietHistoryDetailsDTO;
import com.hampcode.exception.BadRequestException;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.DietHistoryMapper;
import com.hampcode.model.entity.*;
import com.hampcode.repository.MealRepository;
import com.hampcode.repository.UserRepository;
import org.springframework.data.domain.PageImpl;
import com.hampcode.repository.DietHistoryRespository;
import com.hampcode.service.DietHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DietHistoryServiceImpl implements DietHistoryService {
    private final DietHistoryRespository dietHistoryRespository;
    private final DietHistoryMapper dietHistoryMapper;
    private final UserRepository userRepository;
    private final MealRepository mealRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DietHistoryDetailsDTO> findAll() {
        List<DietHistory> d = dietHistoryRespository.findAll();
        return d.stream()
                .map(dietHistoryMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DietHistoryDetailsDTO> paginate(Integer id_user, Pageable pageable) {
        Page<DietHistory> d = dietHistoryRespository.findByUserId(id_user, pageable);
        List<DietHistoryDetailsDTO> dietHistoryDetailsDTOs = d.stream()
                .map(dietHistoryMapper::toDetailsDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dietHistoryDetailsDTOs, pageable, d.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public DietHistoryDetailsDTO findById(Integer id) {
        DietHistory d = dietHistoryRespository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Diet History not found with id: " + id));
        return dietHistoryMapper.toDetailsDTO(d);
    }

    @Transactional
    @Override
    public DietHistoryDetailsDTO create(Integer id_user, Integer id_meal, DietHistoryCUDTO dietCreateDTO) {
        User us = userRepository.findById(id_user).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id_user));
        Meal m = mealRepository.findById(id_meal).orElseThrow(()->new ResourceNotFoundException("Meal not found with id: "+ id_meal));
        dietHistoryRespository.findByUserIdMealTypeAndDate(id_user, dietCreateDTO.getMealType(), dietCreateDTO.getDate())
                .ifPresent(existingDietHistory -> {
                    throw new BadRequestException("This meal type has already been logged for this user on the specified date.");
                });
        DietHistory d = dietHistoryMapper.toEntity(dietCreateDTO);
        d.setUser(us);
        d.setMeal(m);
        return dietHistoryMapper.toDetailsDTO(dietHistoryRespository.save(d));
    }

    @Transactional
    @Override
    public DietHistoryDetailsDTO update(Integer id, Integer id_user, Integer id_meal, DietHistoryCUDTO dietUpdateDTO) {
        DietHistory aux = dietHistoryRespository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Diet History not found with id: " + id));
        dietHistoryRespository.findByUserIdMealTypeAndDate(id_user, dietUpdateDTO.getMealType(), dietUpdateDTO.getDate())
                .ifPresent(existingDietHistory -> {
                    throw new BadRequestException("This meal type has already been logged for this user on the specified date.");
                });
        if(id_meal != null)
        {
            Meal m = mealRepository.findById(id_meal).orElseThrow(()->new ResourceNotFoundException("Meal not found with id: "+ id_meal));
            aux.setMeal(m);
        }

        aux.setDate(dietUpdateDTO.getDate());
        aux.setMealType(dietUpdateDTO.getMealType());
        aux.setPortion_quantity(dietUpdateDTO.getPortion_quantity());

        return dietHistoryMapper.toDetailsDTO(dietHistoryRespository.save(aux));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        DietHistory d = dietHistoryRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Diet history not found with id: " + id));
        dietHistoryRespository.delete(d);
    }
}
