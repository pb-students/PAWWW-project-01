package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userList (Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "showusers";
    }

    @PostMapping("/changerole/{user}/{role}")
    public String userRole (
            @PathVariable("user") String userID, @PathVariable("role") String role,
             Model model
    ) {

        System.out.println("Jesteśmy tutaj");
        System.out.println("User id to" + userID);
        System.out.println("rola to" + role);
        User user = userRepository.findById(Integer.parseInt(userID)).orElse(new User());
        user.setRole(role);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "showusers";
    }

}
