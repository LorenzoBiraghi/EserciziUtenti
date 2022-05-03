package com.esercizio.utenti.controller.api;

import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.service.api.AddressService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * RestController of  address
 *
 * @author lorenzoBiraghi
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    AddressService addressService;

    /**
     * Get all addresses from database
     * @return List of Address
     */
    @ApiResponse(description = "get addresses",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
    @GetMapping("/")
    public ResponseEntity<List<Address>> getAllAddress(){
        List<Address> addresses = addressService.findAll();
        return ResponseEntity.ok(addresses);
    }

    /**
     * Find a address by his id
     * @param id of address
     * @return address with this id
     */
    @ApiResponse(description = "get address",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        Address address = addressService.findById(id);
        if(address != null){
            return ResponseEntity.ok(address);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add a new Address
     * @param address the User object you want to add
     * @return the Address that you add
     */
    @ApiResponse(description = "added address",responseCode = "200", content = @Content(schema = @Schema(implementation = Address.class)))
    @PostMapping("/")
    public ResponseEntity<Address> addNewAddress(@Valid @RequestBody Address address){
        addressService.save(address);
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
    public ResponseEntity<Address> updateAddressByIdd(@Valid @RequestBody Address address, @PathVariable Long id){
        Address addressVerication = addressService.findById(id);
        if(addressVerication != null){
            addressService.save(address);
            return ResponseEntity.ok(address);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Delete all addresses on database
     * @return void
     */
    @ApiResponse(description = "delete all addresses",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
    @DeleteMapping("/")
    public ResponseEntity<Address> deleteAllAddresses(){
        addressService.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a address by his id
     * @param id the address' id that you want delete
     * @return void
     */
    @ApiResponse(description = "delete address",responseCode = "200", content = @Content(schema = @Schema(implementation = void.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddressById(@PathVariable Long id){
        Address address = addressService.findById(id);
        if(address != null){
            addressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
