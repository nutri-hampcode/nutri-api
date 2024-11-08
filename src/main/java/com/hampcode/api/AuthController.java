package com.hampcode.api;

import com.hampcode.dto.AuthResponseDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserRegistrationDTO;
import com.hampcode.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Endpoint para registrar clientes
    @PostMapping("/register")
    public ResponseEntity<UserCUDTO> registerCustomer(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        System.out.println(userRegistrationDTO);
        UserCUDTO userProfile = userService.create(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid  @RequestBody UserLoginDTO loginDTO) {
        AuthResponseDTO authResponse = userService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
