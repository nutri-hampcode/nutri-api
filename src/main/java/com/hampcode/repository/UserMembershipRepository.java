package com.hampcode.repository;

import com.hampcode.model.entity.UserMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserMembershipRepository extends JpaRepository<UserMembership, Integer> {
    Optional<UserMembership> findByUserId(Integer userId);
    boolean existsByUserId(Integer userId);
}
