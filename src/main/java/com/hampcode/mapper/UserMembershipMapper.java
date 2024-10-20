package com.hampcode.mapper;

import com.hampcode.dto.UserMembershipCreateUpdateDTO;
import com.hampcode.dto.UserMembershipDetailsDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Membership;
import com.hampcode.model.entity.UserMembership;
import com.hampcode.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMembershipMapper {

    private final MembershipRepository membershipRepository;

    @Autowired
    public UserMembershipMapper(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public UserMembership toUserMembership(UserMembershipCreateUpdateDTO userMembershipDto) {
        UserMembership userMembership = new UserMembership();
        userMembership.setStart_date(userMembershipDto.getStart_date());
        userMembership.setEnd_date(userMembershipDto.getEnd_date());

        // Buscar la membresía existente
        Membership membership = membershipRepository
                .findById(userMembershipDto.getMembership_id())
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found"));

        userMembership.setMembership(membership);
        userMembership.setStatus(true);
        return userMembership;
    }

    public static UserMembershipDetailsDTO toDetailsDTO(UserMembership userMembership) {
        UserMembershipDetailsDTO dto = new UserMembershipDetailsDTO();
        dto.setName(userMembership.getMembership().getName().name());
        dto.setStart_date(userMembership.getStart_date());
        dto.setEnd_date(userMembership.getEnd_date());
        dto.setPrice(userMembership.getMembership().getPrice());
        dto.setStatus(userMembership.isStatus());
        return dto;
    }
}