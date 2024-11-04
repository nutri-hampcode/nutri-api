package com.hampcode.mapper;

import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.model.entity.User;
import com.hampcode.dto.AuthResponseDTO;
import com.hampcode.dto.UserRegistrationDTO;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;


    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toUserEntity(UserRegistrationDTO userRegistrationDTO) {
        return modelMapper.map(userRegistrationDTO, User.class);
    }

    public UserCUDTO toUserCUDTO(User user) {
        UserCUDTO userCUDTO = modelMapper.map(user, UserCUDTO.class);
        if(user.getCustomer()!=null) {
            userCUDTO.setName(user.getCustomer().getName());
            userCUDTO.setUsername(user.getCustomer().getUsername());
            userCUDTO.setHeight(user.getCustomer().getHeight());
            userCUDTO.setWeight(user.getCustomer().getWeight());
            userCUDTO.setAge(user.getCustomer().getAge());
            if(user.getCustomer().getDietType()!=null) {
                userCUDTO.setDietTypeId(user.getCustomer().getDietType().getId());
            }
        }
        return userCUDTO;
    }

    public User toUserLoginEntity(UserLoginDTO userLoginDTO) {
        return modelMapper.map(userLoginDTO, User.class);
    }

    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        String name = user.getCustomer().getName();

        authResponseDTO.setId(user.getId());
        authResponseDTO.setName(name);

        authResponseDTO.setRole(user.getRole().getName().name());

        return authResponseDTO;

    }
}