package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestController of  user
 *
 * @author lorenzoBiraghi
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Lazy
    @Autowired
    UserRepository userRepository;

    /**
     * Get all users from database
     * @return List of User
     */

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Find a user by his id
     * @param id of user
     * @return User with this id
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add a new User
     * @param user the User object you want to add
     * @return the User that you add
     */
    @PostMapping("/")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Update a User
     * @param user the User object you want to update
     * @param id the user's id that you want update
     * @return the User that you update
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Delete all users on database
     * @return void
     */

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllUsers(){
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a user by his id
     * @param id the user's id that you want delete
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }
}
