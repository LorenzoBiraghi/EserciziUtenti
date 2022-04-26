package com.esercizio.utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserIterface userIterface;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userIterface.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<User> user = userIterface.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        userIterface.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable Long id){
        Optional<User> userOptional = userIterface.findById(id);
        if (userOptional.isPresent()){
            userIterface.save(user);
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllUsers(){
        userIterface.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        Optional<User> userOptional = userIterface.findById(id);
        if (userOptional.isPresent()){
            userIterface.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }
}
