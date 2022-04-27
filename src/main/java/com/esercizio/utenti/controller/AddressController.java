package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.repository.AddresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddresRepository addresRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllAddress(){
        List<Address> addresses = addresRepository.findAll();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<Address> address = addresRepository.findById(id);
        if(address.isPresent()){
            return ResponseEntity.ok(address);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewUser(@RequestBody Address user){
        addresRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody Address address, @PathVariable Long id){
        Optional<Address> addressOptional = addresRepository.findById(id);
        if (addressOptional.isPresent()){
            addresRepository.save(address);
            return ResponseEntity.ok(address);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllUsers(){
        addresRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        Optional<Address> addressOptional = addresRepository.findById(id);
        if (addressOptional.isPresent()){
            addresRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }
}
