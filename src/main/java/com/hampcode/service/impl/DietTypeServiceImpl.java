package com.hampcode.service.impl;

import com.hampcode.dto.DietTypeDTO;
import com.hampcode.exception.BadRequestException;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.DietTypeMapper;
import com.hampcode.model.entity.DietType;
import com.hampcode.repository.DietTypeRepository;
import com.hampcode.service.DietTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DietTypeServiceImpl implements DietTypeService {
    private final DietTypeRepository dietTypeRepository;
    private final DietTypeMapper dietTypeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<DietTypeDTO> findAll() {
        List<DietType> dt = dietTypeRepository.findAll();
        return dt.stream().map(dietTypeMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public DietTypeDTO getOne(Integer id) {
        DietType dt = dietTypeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Diet Type not found with id: " + id));
        return dietTypeMapper.toDTO(dt);
    }

    @Transactional
    @Override
    public DietTypeDTO create(DietTypeDTO dietTypeDTO) {
        dietTypeRepository.findDietTypeByType(dietTypeDTO.getType())
                .ifPresent(existingDietType -> {throw new BadRequestException("Diet Type already exists");
                });
        DietType dt = dietTypeMapper.toEntity(dietTypeDTO);
        dietTypeRepository.save(dt);
        return dietTypeMapper.toDTO(dt);
    }

    @Transactional
    @Override
    public DietTypeDTO update(Integer id, DietTypeDTO dietTypeDTO) {
        DietType dt = dietTypeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Diet Type not found with id: " + id));

        dietTypeRepository.findDietTypeByType(dietTypeDTO.getType())
                .filter(existingDietType -> !existingDietType.getId().equals(id))
                .ifPresent(existingDietType -> {throw new BadRequestException("Diet Type already exists");
                });

        dt.setType(dietTypeDTO.getType());
        dt.setDescription(dietTypeDTO.getDescription());
        dietTypeRepository.save(dt);

        return dietTypeMapper.toDTO(dt);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        DietType dt = dietTypeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Diet Type not found with id: " + id));
        dietTypeRepository.deleteById(id);
    }
}