package com.hampcode.service;

import com.hampcode.dto.UserMembershipCreateUpdateDTO;
import com.hampcode.dto.UserMembershipDetailsDTO;

import java.util.List;

public interface UserMembershipService {
    List<UserMembershipDetailsDTO> findAll();
    UserMembershipDetailsDTO findById(Integer userId);
    UserMembershipDetailsDTO confirmMembership(Integer userId);
    UserMembershipDetailsDTO create(Integer userId, UserMembershipCreateUpdateDTO userMembershipDto);
    UserMembershipDetailsDTO update(Integer userId, UserMembershipCreateUpdateDTO userMembershipDto);
    UserMembershipDetailsDTO delete(Integer userId);
}