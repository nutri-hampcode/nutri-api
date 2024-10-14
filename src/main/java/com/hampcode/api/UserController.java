package com.hampcode.api;

import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.service.UserService;
import com.hampcode.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/register")
    public ResponseEntity<UserCUDTO> register(@Valid @RequestBody UserCUDTO userDTO){
        UserCUDTO u = userService.create(userDTO);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        if (userService.checkCredentials(userLoginDTO.getUsername(),userLoginDTO.getPassword())){
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid login", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/recover")
    public ResponseEntity<String> recover(@Valid @RequestBody UserCUDTO userDTO){
        try{
            userService.recoverAccount(userDTO.getEmail(),userDTO.getPassword());
            return ResponseEntity.ok("Password updated successfully");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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

