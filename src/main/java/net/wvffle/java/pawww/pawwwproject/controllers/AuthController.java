package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import net.wvffle.java.pawww.pawwwproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, String> login (
            @RequestParam(required = false) String usernameError,
            @RequestParam(required = false) String passwordError
    ) {
        // TODO: Implement login
        Map<String, String> map = new HashMap<>();
        map.put("username", usernameError);
        map.put("password", passwordError);
        return  map;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult
    ) {
        if (userRepository.existsByUsername(user.getUsername())) {
            bindingResult.addError(new FieldError("user", "username", "User already exists"));
        }

        if (bindingResult.hasErrors()) {
            // Return 400 error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        return ResponseEntity.ok(user);
    }
}
