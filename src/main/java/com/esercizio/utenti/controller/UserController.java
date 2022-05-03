package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.RoleAuth;
import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * RestController of  user
 *
 * @author lorenzoBiraghi
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Get all users from database
     * @return List of User
     */

    @ApiResponse(description = "get users",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
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
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find a user by Username
     * @param username
     * @return User with this Username
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    /**
     * Add a new User
     * @param user the User object you want to add
     * @return the User that you add
     */

    @ApiResponse(description = "added user",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @PostMapping("/")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user){
        User userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }

    /**
     * Add to a user a Role
     * @param username
     * @param request
     * @return User object
     */
    @PostMapping("/{username}/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @RequestBody RoleAuth request) {
        User userEntity = userService.addRoleToUser(username, request.getName());
        return ResponseEntity.ok(userEntity);
    }

    /**
     * Update a User
     * @param user the User object you want to update
     * @param id the user's id that you want update
     * @return the User that you update
     */

    @ApiResponse(description = "get user updated",responseCode = "200", content = @Content(schema = @Schema(implementation = User.class)))
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable Long id){
        User userOptional = userService.findById(id);
        if (userOptional!= null){
            userService.save(user);
            return ResponseEntity.ok(user);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Delete all users on database
     * @return void
     */

    @ApiResponse(description = "delete all users",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
    @DeleteMapping("/")
    public ResponseEntity<User> deleteAllUsers(){
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
    public ResponseEntity<User> deleteUserById(@PathVariable Long id){
        User userOptional = userService.findById(id);
        if (userOptional != null){
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
