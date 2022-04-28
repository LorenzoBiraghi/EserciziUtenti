package com.esercizio.utenti.service;

import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service of  User
 *
 * @author lorenzoBiraghi
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * FindAll Users
     * @return List of Users
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Find by id a user
     * @param id user's id
     * @return Optional User object
     */
    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else{
            return null;
        }

    }

    /**
     * Save a object User
     * @param user Object user
     */
    public void save(User user){
        userRepository.save(user);
    }

    /**
     * Delete all Users
     */
    public void deleteAll(){
        userRepository.deleteAll();
    }

    /**
     * Delete a user
     * @param id user's id
     */
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
