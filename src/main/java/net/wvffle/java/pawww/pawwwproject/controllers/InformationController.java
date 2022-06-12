package net.wvffle.java.pawww.pawwwproject.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import net.wvffle.java.pawww.pawwwproject.components.session.SessionSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InformationController {
    @Autowired
    private InformationRepository informationRepository;

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
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        model.addAttribute("information", information);
        return "information/read";
    }
    
    @PostMapping("/publish")
    public String publish (HttpServletRequest request, @ModelAttribute Information information) {
        information.setAuthor(userRepository.findByUsername(request.getRemoteUser()));
        information.setCreated(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        informationRepository.save(information);
        return "redirect:/information/by-me";
    }
}
