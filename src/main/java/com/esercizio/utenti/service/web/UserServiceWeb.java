package com.esercizio.utenti.service.web;

import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserServiceWeb {

    @Autowired
    UserRepository userRepository;

    private boolean existsById(Long id){
        return userRepository.existsById(id);
    }

    public User findById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        else return user;
    }

    public List<User> findAll(int pageNumber, int rowPerPage) {
        List<User> users = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        userRepository.findAll(sortedByIdAsc).forEach(users::add);
        return users;
    }

    public User save(User user){
        if (!StringUtils.isEmpty(user.getFirstname())) {
            if (user.getId() != null && existsById(user.getId())) {
                return null;
            }
            return userRepository.save(user);
        }
        else {
            return null;
        }
    }

    public void update(User user)
            throws Exception {
        if (!StringUtils.isEmpty(user.getFirstname())) {
            if (!existsById(user.getId())) {
                throw new Exception("Cannot find user with id: " + user.getId());
            }
            userRepository.save(user);
        }
        else {
            Exception exc = new Exception("Failed to save user");
            exc.getMessage();
            throw exc;
        }
    }

    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find user with id: " + id);
        }
        else {
            userRepository.deleteById(id);
        }
    }

    public Long count() {
        return userRepository.count();
    }
}
