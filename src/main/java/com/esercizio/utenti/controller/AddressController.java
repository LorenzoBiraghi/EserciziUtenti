package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.repository.AddressRepository;
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
    AddressRepository addresRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllAddress(){
        List<Address> addresses = addresRepository.findAll();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id){
        Optional<Address> address = addresRepository.findById(id);
        if(address.isPresent()){
            return ResponseEntity.ok(address);
        }
        else{
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewAddress(@RequestBody Address user){
        addresRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddressByIdd(@RequestBody Address address, @PathVariable Long id){
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
    public ResponseEntity<?> deleteAllAddresses(){
        addresRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable Long id){
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
