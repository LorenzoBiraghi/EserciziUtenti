package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.RoleAuth;
import com.esercizio.utenti.entity.UserAuth;
import com.esercizio.utenti.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usersauth")
@RequiredArgsConstructor
@Slf4j
public class UserAuthController {
    private final UserAuthService userService;

    @GetMapping
    public ResponseEntity<List<UserAuth>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserAuth> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserAuth> save(@RequestBody UserAuth user) {
        UserAuth userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }


    @PostMapping("/{username}/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @RequestBody RoleAuth request) {
        UserAuth userEntity = userService.addRoleToUser(username, request.getName());
        return ResponseEntity.ok(userEntity);
    }
}
