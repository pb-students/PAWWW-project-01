package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import net.wvffle.java.pawww.pawwwproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login (
            Model model,
            @RequestParam(required = false) String usernameError,
            @RequestParam(required = false) String passwordError
    ) {
        model.addAttribute("usernameError", usernameError);
        model.addAttribute("passwordError", passwordError);

        return "login";
    }

    @GetMapping("/register")
    public String register (
            Model model,
            @RequestParam(required = false) String usernameError,
            @RequestParam(required = false) String passwordError
    ) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            bindingResult.addError(new FieldError("user", "username", "User already exists"));
            return "register";
        }

        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);

        // NOTE: We're now sure that passwords are matching as we've validated them previously
        user.setMatchingPassword(encoded);

        userRepository.save(user);

        UserDetails userDetails = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}
