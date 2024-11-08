package com.hampcode.api;

import com.hampcode.dto.AppointmentDetailsDTO;
import com.hampcode.dto.UserMembershipCreateUpdateDTO;
import com.hampcode.dto.UserMembershipDetailsDTO;
import com.hampcode.service.UserMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userMemberships")
public class UserMembershipController {
    private final UserMembershipService userMembershipService;

    @GetMapping
    public ResponseEntity<List<UserMembershipDetailsDTO>> list() {
        List<UserMembershipDetailsDTO> userMemberships = userMembershipService.findAll();
        return new ResponseEntity<>(userMemberships, HttpStatus.OK);
    }
    // Obtener Membership por ID del usuario
    @GetMapping("/{userId}")
    public ResponseEntity<UserMembershipDetailsDTO> getOne(@PathVariable Integer userId) {
        UserMembershipDetailsDTO um = userMembershipService.findById(userId);
        return new ResponseEntity<>(um, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserMembershipDetailsDTO> create(@PathVariable Integer userId, @RequestBody UserMembershipCreateUpdateDTO userMembershipDto) {
        UserMembershipDetailsDTO um = userMembershipService.create(userId, userMembershipDto);
        return new ResponseEntity<>(um, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserMembershipDetailsDTO> update(@PathVariable Integer userId, @RequestBody UserMembershipCreateUpdateDTO userMembershipDto) {
        UserMembershipDetailsDTO um = userMembershipService.update(userId, userMembershipDto);
        return new ResponseEntity<>(um, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserMembershipDetailsDTO> delete(@PathVariable Integer userId) {
        UserMembershipDetailsDTO um = userMembershipService.delete(userId);
        return new ResponseEntity<>(um, HttpStatus.OK);
    }
}