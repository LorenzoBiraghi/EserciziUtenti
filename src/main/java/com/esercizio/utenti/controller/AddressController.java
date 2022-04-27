package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * RestController of  address
 *
 * @author lorenzoBiraghi
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Lazy
    @Autowired
    AddressRepository addresRepository;

    /**
     * Get all addresses from database
     * @return List of Address
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllAddress(){
        List<Address> addresses = addresRepository.findAll();
        return ResponseEntity.ok(addresses);
    }

    /**
     * Find a address by his id
     * @param id of address
     * @return address with this id
     */
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

    /**
     * Add a new Address
     * @param address the User object you want to add
     * @return the Address that you add
     */
    @PostMapping("/")
    public ResponseEntity<?> addNewAddress(@RequestBody Address address){
        addresRepository.save(address);
        return ResponseEntity.ok(address);
    }

    /**
     * Update a Address
     * @param address the Address object you want to update
     * @param id the address' id that you want update
     * @return the Address that you update
     */
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

    /**
     * Delete all addresses on database
     * @return void
     */
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAllAddresses(){
        addresRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a address by his id
     * @param id the address' id that you want delete
     * @return void
     */
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
