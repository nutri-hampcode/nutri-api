package com.hampcode.mapper;

import com.hampcode.dto.DietHistoryCUDTO;
import com.hampcode.dto.DietHistoryDetailsDTO;
import com.hampcode.dto.MealDetailsDTO;
import com.hampcode.model.entity.DietHistory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class DietHistoryMapper {
    private final ModelMapper modelMapper;
    private final MealMapper mealMapper;
    public DietHistoryMapper(ModelMapper modelMapper, MealMapper mealMapper) {
        this.modelMapper = modelMapper;
        this.mealMapper = mealMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public DietHistoryDetailsDTO toDetailsDTO(DietHistory dietHistory) {
        DietHistoryDetailsDTO dietHistoryDetailsDTO = modelMapper.map(dietHistory, DietHistoryDetailsDTO.class);

        dietHistoryDetailsDTO.setUser_name(dietHistory.getUser().getCustomer().getName());
        MealDetailsDTO mealDetailsDTO = mealMapper.toDetailsDTO(dietHistory.getMeal());
        dietHistoryDetailsDTO.setMeal(mealDetailsDTO);
        dietHistoryDetailsDTO.setMeal_type(dietHistory.getMealType());
        return dietHistoryDetailsDTO;
    }

    public DietHistory toEntity(DietHistoryCUDTO dietHistoryCUDTO) {
        return modelMapper.map(dietHistoryCUDTO, DietHistory.class);
    }

    public DietHistoryCUDTO toCreateUpdateDTO(DietHistory dietHistory) {
        return modelMapper.map(dietHistory, DietHistoryCUDTO.class);
    }
}