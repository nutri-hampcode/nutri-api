package com.hampcode.mapper;

import com.hampcode.dto.*;
import com.hampcode.model.entity.Meal;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {
    private final ModelMapper modelMapper;
    public MealMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public MealDetailsDTO toDetailsDTO(Meal meal) {
        MealDetailsDTO mealDetailsDTO = modelMapper.map(meal, MealDetailsDTO.class);
        mealDetailsDTO.setDietType(meal.getDietType().getType());
        mealDetailsDTO.setImage(meal.getImage().getBytes());
        return mealDetailsDTO;
    }

    public Meal toEntity(MealCUDTO mealCUDTO) {
        return modelMapper.map(mealCUDTO, Meal.class);
    }

    public MealCUDTO toCreateUpdateDTO(Meal meal) {
        return modelMapper.map(meal, MealCUDTO.class);
    }}
