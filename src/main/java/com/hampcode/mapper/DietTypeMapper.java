package com.hampcode.mapper;

import com.hampcode.dto.DietTypeDTO;
import com.hampcode.model.entity.DietType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DietTypeMapper {
    private final ModelMapper modelMapper;
    public DietTypeMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}
    public DietTypeDTO toDTO(DietType dietType) {return modelMapper.map(dietType, DietTypeDTO.class);}
    public DietType toEntity(DietTypeDTO dietTypeDTO) {return modelMapper.map(dietTypeDTO, DietType.class);}
}
