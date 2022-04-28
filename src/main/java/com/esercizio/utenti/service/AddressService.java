package com.esercizio.utenti.service;

import com.esercizio.utenti.entity.Address;
import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service of  Address
 *
 * @author lorenzoBiraghi
 */
@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * Find all Addresses
     * @return List of Addresses
     */
    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    /**
     * Find by id a address
     * @param id address' id
     * @return Optional Address object
     */
    public Address findById(Long id){
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()){
            return address.get();
        }
        else{
            return null;
        }
    }

    /**
     * Save a object Address
     * @param address Object Address
     */
    public void save(Address address){
        addressRepository.save(address);
    }

    /**
     * Delete all Addresses
     */
    public void deleteAll(){
        addressRepository.deleteAll();
    }

    /**
     * Delete a address
     * @param id address' id id
     */
    public void deleteById(Long id){
        addressRepository.deleteById(id);
    }
}
