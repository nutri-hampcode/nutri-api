package com.hampcode.service.impl;

import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.model.entity.User;
import com.hampcode.repository.UserRepository;
import com.hampcode.repository.GoalRepository;
import com.hampcode.repository.DietTypeRepository;
import com.hampcode.service.UserService;
import com.hampcode.mapper.UserMapper;
import com.hampcode.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final DietTypeRepository dietTypeRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserCUDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserCUDTO getOne(Integer id) {
        User user= userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Transactional
    @Override
    public UserCUDTO create(UserCUDTO userDTO) {
        User user= userMapper.toEntity(userDTO);
        user.setPassword(user.getPassword());
        User saveuser= userRepository.save(user);
        return userMapper.toDTO(saveuser);
    }

    @Transactional
    @Override
    public UserCUDTO update(Integer id, UserCUDTO user) {
        User us = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        us.setName(user.getName());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            us.setPassword(user.getPassword());
        }
        us.setUsername(user.getUsername());
        us.setEmail(user.getEmail());
        us.setHeight(user.getHeight());
        us.setWeight(user.getWeight());
        us.setAge(user.getAge());
        us.setAllergies(user.getAllergies());
        us.setGoal(goalRepository.findById(user.getGoalId()).orElse(null));
        us.setDietType(dietTypeRepository.findById(user.getDietTypeId()).orElse(null));

        User updateUser = userRepository.save(us);
        return userMapper.toDTO(updateUser);
    }

    @Transactional
    @Override
    public UserCUDTO findByEmail(String email){
        User user= userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toDTO(user);
    }

    @Transactional
    @Override
    public void recoverAccount(String email,String newPassword) {
        User us = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        us.setPassword(newPassword);
        userRepository.save(us);
    }


    @Transactional
    @Override
    public UserCUDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return userMapper.toDTO(user);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return user.getPassword().equals(password);
    }
}