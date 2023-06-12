package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public Iterable<User> userList () {
        return userRepository.findAll();
    }

    @PostMapping("/changerole/{id}")
    public ResponseEntity<User> userRole (
            @PathVariable String id,
            @RequestParam String role
    ) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();
        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
