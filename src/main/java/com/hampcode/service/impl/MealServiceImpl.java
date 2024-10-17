package com.hampcode.service.impl;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.dto.MealDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Meal;
import com.hampcode.repository.MealRepository;
import com.hampcode.service.MealService;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    // Método para crear una nueva comida usando MealDTO
    @Override
    public Meal createMeal(MealDTO mealDto) {
        Meal meal = new Meal(); // Convertir DTO a entidad
        meal.setName(mealDto.getName());
        meal.setDescription(mealDto.getDescription());
        meal.setCalories(mealDto.getCalories()); // Agregado
        meal.setProteins(mealDto.getProteins()); // Agregado
        meal.setCarbs(mealDto.getCarbs()); // Agregado
        meal.setFat(mealDto.getFat()); // Agregado
        meal.setDietType(mealDto.getDietType()); // Agregado

        Meal savedMeal = mealRepository.save(meal);
        return savedMeal;
    }

    // Método para obtener todas las comidas
    @Override
    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    // Método para obtener una comida por ID
    @Override
    public Meal findMealById(Integer id) {
        return mealRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comida no encontrada con ID: " + id));
    }

    // Método para actualizar una comida existente usando MealDTO
    @Override
    public Meal updateMeal(Integer id, MealDTO mealDto) {
        Meal existingMeal = findMealById(id); // Llama al método para obtener la comida

        // Actualiza los campos de la comida existente
        existingMeal.setName(mealDto.getName());
        existingMeal.setDescription(mealDto.getDescription());
        existingMeal.setCalories(mealDto.getCalories()); // Agregado
        existingMeal.setProteins(mealDto.getProteins()); // Agregado
        existingMeal.setCarbs(mealDto.getCarbs()); // Agregado
        existingMeal.setFat(mealDto.getFat()); // Agregado
        existingMeal.setDietType(mealDto.getDietType()); // Agregado

        return mealRepository.save(existingMeal); // Guarda la comida actualizada
    }

    // Método para eliminar una comida
    @Override
    public void deleteMeal(Integer id) {
        Meal existingMeal = findMealById(id); // Verifica si la comida existe
        mealRepository.delete(existingMeal); // Elimina la comida
    }
//COMENTARIO PARA VERIFICAR QUE SI CAMBIA LOLOLL
    /* Método privado para convertir una entidad Meal a MealDTO (opcional)
    private MealDTO convertToDTO(Meal meal) {
        MealDTO dto = new MealDTO();
        dto.setName(meal.getName());
        dto.setDescription(meal.getDescription());
        dto.setCalories(meal.getCalories()); // Agregado
        dto.setProteins(meal.getProteins()); // Agregado
        dto.setCarbs(meal.getCarbs()); // Agregado
        dto.setFat(meal.getFat()); // Agregado
        dto.setDietType(meal.getDietType()); // Agregado
        return dto;
    }*/
}
=======
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Meal m = mealRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Meal not found with id: " + id));
        mealRepo.deleteById(id);
    }
}
>>>>>>> origin/feature/crud-diethistory
