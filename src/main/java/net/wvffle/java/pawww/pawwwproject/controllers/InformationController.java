package net.wvffle.java.pawww.pawwwproject.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import net.wvffle.java.pawww.pawwwproject.components.session.SessionSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;

import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
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
    public String list (HttpServletRequest request, Model model) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        model.addAttribute("informations", informationRepository.findAllByAuthor(user, sessionSort.getSort()));
        return "information/list";
    }

    @PostMapping("/information/by-me/sort")
    public String listOrdered (@RequestParam String ordering, @RequestParam boolean descending) {
        sessionSort.setOrdering(ordering);
        sessionSort.setDescending(descending);
        return "redirect:/information/by-me";
    }

    @GetMapping("/information/read/{id}")
    public String read (Model model, @PathVariable("id") String id) {
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(null);
        // TODO: check if user is actually permitted to view this information
        model.addAttribute("userAuthorized", true);
        model.addAttribute("information", information);
        return "information/read";
    }
    
    @PostMapping("/publish")
    public String publish (@ModelAttribute @Valid Information information, BindingResult bindingResult, HttpServletRequest request, Model model) {
        List<FieldError> errors = bindingResult.getFieldErrors().stream()
            .filter(err -> !err.getField().equals("author") && !err.getField().equals("created"))
            .toList();

        if (errors.size() > 0) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "wall";
        }
        
        information.setAuthor(userRepository.findByUsername(request.getRemoteUser()));
        information.setCreated(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        informationRepository.save(information);
        return "redirect:/information/by-me";
    }
    
    @GetMapping("/information/edit/{id}")
    public String edit (HttpServletRequest request, Model model, @PathVariable("id") String id) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        model.addAttribute("userAuthorized", information.getAuthor() == user);
        
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("information", information);
        return "information/edit";
    }
    
    @PostMapping("/information/edit")
    public String edit (@ModelAttribute @Valid Information information, BindingResult bindingResult, HttpServletRequest request, Model model) {
        List<FieldError> errors = bindingResult.getFieldErrors().stream()
            .filter(err -> !err.getField().equals("author") && !err.getField().equals("created"))
            .toList();

        if (errors.size() > 0) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "information/edit/";
        }
        
        User user = userRepository.findByUsername(request.getRemoteUser());
        if (information.getAuthor() != user) {
            model.addAttribute("userAuthorized", false);
            model.addAttribute("categories", categoryRepository.findAll());
            return "information/edit";
        }
        
        informationRepository.save(information);
        return "redirect:/information/read/" + information.getId().toString();
    }
    
    @GetMapping("/information/delete/{id}")
    public String deleteGet (HttpServletRequest request, Model model, @PathVariable("id") String id) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        if (information.getAuthor() == user) model.addAttribute("userAuthorized", true);
        else model.addAttribute("userAuthorized", false);
        return "information/delete";
    }
    
    @PostMapping("/information/delete/{id}")
    public String deletePost (HttpServletRequest request, Model model, @PathVariable("id") String id) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        if (information.getAuthor() != user) {
            model.addAttribute("userAuthorized", false);
            return "information/delete";
        }
        
        informationRepository.delete(information);
        return "redirect:/information/by-me";
    }
}
