package com.hampcode.mapper;

import com.hampcode.dto.DoctorDTO;
import com.hampcode.model.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    private final ModelMapper modelMapper;
    public DoctorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public DoctorDTO toDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }
    public Doctor toEntity(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }
}
