package net.wvffle.java.pawww.pawwwproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.spring5.view.ThymeleafView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    @RequestMapping("/login")
    public String login (
            Model model,
            @RequestParam(required = false) String usernameError,
            @RequestParam(required = false) String passwordError
    ) {
        if (usernameError != null && !usernameError.equalsIgnoreCase("null")) {
            model.addAttribute("usernameError", usernameError);
        }
        if (passwordError != null && !passwordError.equalsIgnoreCase("null")) {
            model.addAttribute("passwordError", passwordError);
        }
        return "login";
    }
}
