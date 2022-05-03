package com.esercizio.utenti.controller.web;



import com.esercizio.utenti.entity.User;
import com.esercizio.utenti.service.web.UserServiceWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class indexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    UserServiceWeb userServiceWeb;


    @Value("${msg.title}")
    private String title;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<User> users = userServiceWeb.findAll(pageNumber, ROW_PER_PAGE);

        long count = userServiceWeb.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("users", users);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "users-list";
    }

    @GetMapping(value = "/users/{userId}")
    public String getUserById(Model model, @PathVariable long userId) {
        User user = null;
        try {
            user = userServiceWeb.findById(userId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("user", user);
        return "user";
    }



    @GetMapping(value = {"/users/add"})
    public String showAddUser(Model model) {
        User user = new User();
        model.addAttribute("add", true);
        model.addAttribute("user", user);

        return "user-edit";
    }

    @PostMapping(value = "/users/add")
    public String addUsers(Model model,
                             @ModelAttribute("user") User user) {
        try {
            User newContact = userServiceWeb.save(user);
            return "redirect:/users/" + String.valueOf(newContact.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("user", user);
            model.addAttribute("add", true);
            return "user-edit";
        }
    }

    @GetMapping(value = {"/users/{userId}/edit"})
    public String showEditUser(Model model, @PathVariable long userId) {
        User user = null;
        try {
            user = userServiceWeb.findById(userId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "User not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping(value = {"/users/{userId}/edit"})
    public String updateUser(Model model,
                                @PathVariable long userId,
                                @ModelAttribute("user") User user) {
        try {
            user.setId(userId);
            userServiceWeb.update(user);
            return "redirect:/users/" + String.valueOf(user.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "user-edit";
        }
    }
    
}
