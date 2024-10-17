package com.hampcode.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hampcode.dto.UserCUDTO;
import com.hampcode.model.entity.User;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public UserCUDTO toDTO(User user) {
        return modelMapper.map(user, UserCUDTO.class);
    }
    public User toEntity(UserCUDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}