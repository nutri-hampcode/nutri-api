package com.hampcode.service.impl;

import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Membership;
import com.hampcode.repository.MembershipRepository;
import com.hampcode.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MembershipServiceImpl implements MembershipService {
    private final MembershipRepository membershipRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Membership findById(Integer id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
    }

    @Transactional
    @Override
    public Membership create(Membership membership) {
        return membershipRepository.save(membership);
    }

    @Transactional
    @Override
    public Membership update(Integer id, Membership membershipDetails) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));

        membership.setName(membershipDetails.getName());
        membership.setPrice(membershipDetails.getPrice());

        return membershipRepository.save(membership);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
        membershipRepository.delete(membership);
    }
}