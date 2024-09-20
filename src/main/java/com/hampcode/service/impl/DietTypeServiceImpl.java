package com.hampcode.service.impl;

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

    @Transactional(readOnly = true)
    @Override
    public List<DietType> findAll() {
        return dietTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public DietType getOne(Integer id) {
        return dietTypeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Diet Type not found with id: " + id));
    }

    @Transactional
    @Override
    public DietType create(DietType dietType) {
        return dietTypeRepository.save(dietType);
    }

    @Transactional
    @Override
    public DietType update(Integer id, DietType dietType) {
        DietType dt = dietTypeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Diet Type not found with id: " + id));
        dt.setType(dt.getType());
        dt.setDescription(dt.getDescription());
        return dietTypeRepository.save(dt);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        dietTypeRepository.deleteById(id);
    }
}
