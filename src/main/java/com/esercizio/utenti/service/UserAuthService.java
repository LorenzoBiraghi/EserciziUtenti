package com.esercizio.utenti.service;

import com.esercizio.utenti.entity.RoleAuth;
import com.esercizio.utenti.entity.UserAuth;
import com.esercizio.utenti.repository.RoleAuthRepository;
import com.esercizio.utenti.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserAuthService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found";

    private final UserAuthRepository userJpaRepository;
    private final RoleAuthRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAuth save(UserAuth user) {
        log.info("Saving user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJpaRepository.save(user);
    }


    public UserAuth addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        UserAuth userEntity = userJpaRepository.findByUsername(username);
        RoleAuth roleEntity = roleJpaRepository.findByName(roleName);
        userEntity.getRoles().add(roleEntity);
        return userEntity;
    }

    public List<UserAuth> findAll(){
        return userJpaRepository.findAll();
    }

    public UserAuth findByUsername(String username){
        return userJpaRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = userJpaRepository.findByUsername(username);
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
            return new User(user.getUsername(), user.getPassword(), authorities);
        }
    }
}
