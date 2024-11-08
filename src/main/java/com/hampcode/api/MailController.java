package com.hampcode.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.service.PasswordResetTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final PasswordResetTokenService passwordResetTokenService;

    // Enviar correo de restablecimiento de contraseña
    @PostMapping("/sendMail")
    public ResponseEntity<Void> sendPasswordResetMail(@RequestBody String email) throws Exception {
        passwordResetTokenService.createAndSendPasswordResetToken(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Verificar la validez del token de restablecimiento de contraseña
    @GetMapping("/reset/check/{token}")
    public ResponseEntity<Boolean> checkTokenValidity(@PathVariable("token") String token) {
        boolean isValid = passwordResetTokenService.isValidToken(token);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    // Restablecer la contraseña usando el token
    @PostMapping("/reset/{token}")
    public ResponseEntity<Void> resetPassword(@PathVariable("token") String token, @RequestBody String newPassword) {
        passwordResetTokenService.resetPassword(token, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
