package net.wvffle.java.pawww.pawwwproject.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import net.wvffle.java.pawww.pawwwproject.components.session.SessionSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;

import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;

@RestController
public class InformationController {
    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionSort sessionSort;
    
    @GetMapping("/information/by-me")
    public Iterable<Information> list (HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        return informationRepository.findAllByAuthor(user, sessionSort.getSort());
    }

//    @PostMapping("/information/by-me/sort")
//    public String listOrdered (@RequestParam String ordering, @RequestParam boolean descending) {
//        sessionSort.setOrdering(ordering);
//        sessionSort.setDescending(descending);
//        return "redirect:/information/by-me";
//    }

    @GetMapping("/information/read/{id}")
    public Information read (@PathVariable("id") String id) {
        return informationRepository.findById(Integer.parseInt(id)).orElse(null);
    }
    
    @PostMapping("/publish")
    public ResponseEntity<Information> publish (@ModelAttribute @Valid Information information, BindingResult bindingResult, HttpServletRequest request) {
        List<FieldError> errors = bindingResult.getFieldErrors().stream()
            .filter(err -> !err.getField().equals("author") && !err.getField().equals("created"))
            .toList();

        if (errors.size() > 0) {
            return ResponseEntity.badRequest().build();
        }

        information.setAuthor(userRepository.findByUsername(request.getRemoteUser()));
        information.setCreated(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        informationRepository.save(information);
        return ResponseEntity.ok(information);
    }
    
//    @GetMapping("/information/edit/{id}")
//    public String edit (HttpServletRequest request, Model model, @PathVariable("id") String id) {
//        User user = userRepository.findByUsername(request.getRemoteUser());
//        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
//        model.addAttribute("userAuthorized", information.getAuthor() == user);
//
//        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("information", information);
//        return "information/edit";
//    }
    
    @PostMapping("/information/edit")
    public ResponseEntity<Information> edit (@ModelAttribute @Valid Information information, BindingResult bindingResult, HttpServletRequest request) {
        List<FieldError> errors = bindingResult.getFieldErrors().stream()
            .filter(err -> !err.getField().equals("author") && !err.getField().equals("created"))
            .toList();

        if (errors.size() > 0) {
            return ResponseEntity.badRequest().build();
        }
        
        User user = userRepository.findByUsername(request.getRemoteUser());
        if (information.getAuthor() != user) {
            return ResponseEntity.status(403).build();
        }
        
        informationRepository.save(information);
        return ResponseEntity.ok(information);
    }
    
//    @GetMapping("/information/delete/{id}")
//    public String deleteGet (HttpServletRequest request, @PathVariable("id") String id) {
//        User user = userRepository.findByUsername(request.getRemoteUser());
//        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
//        if (information.getAuthor() == user) model.addAttribute("userAuthorized", true);
//        else model.addAttribute("userAuthorized", false);
//        return "information/delete";
//    }
    
    @PostMapping("/information/delete/{id}")
    public ResponseEntity<Information> deletePost (HttpServletRequest request, @PathVariable("id") String id) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        if (information.getAuthor() != user) {
            return ResponseEntity.status(403).build();
        }
        
        informationRepository.delete(information);
        return ResponseEntity.ok(information);
    }
}
