package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userList (Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "showusers";
    }
}
