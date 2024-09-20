package com.hampcode.api;

import com.hampcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hampcode.model.entity.User;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> list(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User u = userService.create(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        if (userService.checkCredentials(user.getUsername(),user.getPassword())){
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid login", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/recover")
    public ResponseEntity<String> recover(@RequestParam String email, @RequestParam String password){
        try{
            userService.recoverAccount(email,password);
            return ResponseEntity.ok("Password updated successfully");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id){
        User u = userService.getOne(id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user){
        User u = userService.update(id, user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

