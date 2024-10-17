package com.hampcode.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hampcode.dto.MealCUDTO;
import com.hampcode.dto.MealDetailsDTO;
import com.hampcode.exception.BadRequestException;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.MealMapper;
import com.hampcode.model.entity.DietType;
import com.hampcode.model.entity.Meal;
import com.hampcode.repository.DietTypeRepository;
import com.hampcode.repository.MealRepository;
import com.hampcode.service.MealService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepo;
    private final MealMapper mealMapper;
    private final DietTypeRepository dietTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MealDetailsDTO> findAll() {
        List<Meal> meals = mealRepo.findAll();
        return meals.stream()
                .map(mealMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MealDetailsDTO> findMealPerDietType(Integer id_diettype) {
        List<Meal> meals = mealRepo.findByDietType(id_diettype)
                .orElseThrow(()-> new ResourceNotFoundException("Meals not found for Diet Type with id: "+id_diettype));
        return meals.stream()
                .map(mealMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public MealDetailsDTO findById(Integer id) {
        Meal m = mealRepo
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Meal not found with id: " + id));
        return mealMapper.toDetailsDTO(m);
    }

    @Transactional
    @Override
    public MealDetailsDTO create(Integer id_diettype, MealCUDTO mealCDTO) {
        DietType dt = dietTypeRepository.findById(id_diettype).orElseThrow(()-> new ResourceNotFoundException("Diet Type not found with id: " + id_diettype));
        mealRepo.findMealByName(mealCDTO.getName())
                .ifPresent(existingMeal -> {
                    if (existingMeal.getDietType().getId().equals(id_diettype)) {
                        throw new BadRequestException("Meal with that Diet Type already exists");
                    }
                });
        Meal m = mealMapper.toEntity(mealCDTO);
        m.setDietType(dt);
        return mealMapper.toDetailsDTO(mealRepo.save(m));
    }

    @Transactional
    @Override
    public MealDetailsDTO update(Integer id, MealCUDTO mealUDTO) {
        Meal m = mealRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Meal not found with id: " + id));

        m.setName(mealUDTO.getName());
        m.setDescription(mealUDTO.getDescription());
        m.setCalories(mealUDTO.getCalories());
        m.setCarbs(mealUDTO.getCarbs());
        m.setProteins(mealUDTO.getProteins());
        m.setFat(mealUDTO.getFat());

        return mealMapper.toDetailsDTO(mealRepo.save(m));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        @SuppressWarnings("unused")
        Meal m = mealRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Meal not found with id: " + id));
        mealRepo.deleteById(id);
    }
}
