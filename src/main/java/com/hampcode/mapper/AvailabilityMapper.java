package com.hampcode.mapper;

import com.hampcode.dto.AvailabilityCreateUpdateDTO;
import com.hampcode.dto.AvailabilityDetailsDTO;
import com.hampcode.model.entity.Availability;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class AvailabilityMapper {
    private final ModelMapper modelMapper;
    public AvailabilityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public AvailabilityDetailsDTO toDetailsDTO(Availability availability) {
        AvailabilityDetailsDTO availabilityDetailsDTO = modelMapper.map(availability, AvailabilityDetailsDTO.class);
        availabilityDetailsDTO.setDoctorName(availability.getDoctor().getFirstName() + " " + availability.getDoctor().getLastName());
        return availabilityDetailsDTO;
    }

    public Availability toEntity(AvailabilityCreateUpdateDTO availabilityCreateUpdateDTO) {
        return modelMapper.map(availabilityCreateUpdateDTO, Availability.class);
    }

    public AvailabilityCreateUpdateDTO toCreateUpdateDTO(Availability availability) {
        return modelMapper.map(availability, AvailabilityCreateUpdateDTO.class);
    }
}
