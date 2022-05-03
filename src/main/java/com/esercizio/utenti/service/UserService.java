package com.esercizio.utenti.service;

import com.esercizio.utenti.entity.RoleAuth;
import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.repository.RoleAuthRepository;
import com.esercizio.utenti.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service of  User
 *
 * @author lorenzoBiraghi
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found";

    private final RoleAuthRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;
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
     * Find a user by Username
     * @param username
     * @return User with this Username
     */
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    /**
     * Save a object User
     * @param user Object user
     * @return user object saved
     */

    public User save(User user){
        log.info("Saving user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    /**
     * Add to a user a Role
     * @param username
     * @param roleName
     * @return User object
     */
    public User addRoleToUser(String username, String roleName){
        log.info("Adding role {} to user {}", roleName, username);
        User userEntity = userRepository.findByUsername(username);
        RoleAuth roleEntity = roleJpaRepository.findByName(roleName);
        userEntity.getRoles().add(roleEntity);
        return userEntity;
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


    /**
     * Login by Username
     * @param username
     * @return User security object
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            log.debug("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }
}
