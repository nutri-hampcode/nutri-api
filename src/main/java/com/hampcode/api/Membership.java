//package com.hampcode.api;
//
//import com.hampcode.model.entity.UserMembership;
//import com.hampcode.service.UserMembershipService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/user-memberships")
//public class UserMembershipController {
//    private final UserMembershipService userMembershipService;
//
//    @GetMapping
//    public ResponseEntity<List<UserMembership>> list() {
//        List<UserMembership> userMemberships = userMembershipService.findAll();
//        return new ResponseEntity<>(userMemberships, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserMembership> get(@PathVariable Integer id) {
//        UserMembership userMembership = userMembershipService.getOne(id);
//        return new ResponseEntity<>(userMembership, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserMembership> create(@RequestBody UserMembership userMembership) {
//        UserMembership um = userMembershipService.create(userMembership);
//        return new ResponseEntity<>(um, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserMembership> update(@PathVariable Integer id, @RequestBody UserMembership userMembership){
//        UserMembership um = userMembershipService.update(id, userMembership);
//        return new ResponseEntity<>(um, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<UserMembership> delete(@PathVariable Integer id){
//        userMembershipService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}