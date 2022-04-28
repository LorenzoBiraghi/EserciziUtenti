package com.esercizio.utenti.controller;

import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.repository.AddressRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Autowired
    AddressRepository addresRepository;

    /**
     * Get all addresses from database
     * @return List of Address
     */
    @ApiResponse(description = "get addresses",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
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
    @ApiResponse(description = "get address",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
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
    @ApiResponse(description = "added address",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
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
    @ApiResponse(description = "updated address",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
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
    @ApiResponse(description = "delete all addresses",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
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
    @ApiResponse(description = "delete address",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
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
