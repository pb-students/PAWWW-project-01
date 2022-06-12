package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/changerole/{id}")
    public String userRole (
            @PathVariable String id,
            @RequestParam String role,
            Model model
    ) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));
        if (optionalUser.isEmpty()) {
            model.addAttribute("userNotFound", id);
            return "redirect:/list";
        }

        model.addAttribute("userNotFound", null);

        User user = optionalUser.get();
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/list";
    }

}
