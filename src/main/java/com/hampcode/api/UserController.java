package com.hampcode.api;

import java.util.List;

import com.hampcode.dto.AuthResponseDTO;
import com.hampcode.dto.UserRegistrationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserCUDTO>> list(){
        List<UserCUDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserCUDTO> get(@PathVariable Integer id){
        UserCUDTO u = userService.getOne(id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCUDTO> update(@PathVariable Integer id, @Valid @RequestBody UserCUDTO userDTO){
        UserCUDTO u = userService.update(id, userDTO);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserCUDTO> delete(@PathVariable Integer id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

