package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserService userService;

    /**
     * Get all users from database
     * @return List of User
     */

    @ApiResponse(description = "get users",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Find a user by his id
     * @param id of user
     * @return User with this id
     */

    @ApiResponse(description = "get user",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
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

    @ApiResponse(description = "added user",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @PostMapping("/")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Update a User
     * @param user the User object you want to update
     * @param id the user's id that you want update
     * @return the User that you update
     */

    @ApiResponse(description = "get user updated",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            userService.save(user);
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

    @ApiResponse(description = "delete all users",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllUsers(){
        userService.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a user by his id
     * @param id the user's id that you want delete
     * @return void
     */

    @ApiResponse(description = "delete user",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }
}
