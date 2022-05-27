package net.wvffle.java.pawww.pawwwproject.controllers;

import java.util.Optional;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;

@Controller
public class ReadController {
    @Autowired
    private InformationRepository informationRepository;


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/read/{id}")
    public String readInformation (Model model, @PathVariable("id") String id) {
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        model.addAttribute("information", information);
        return "readinformation";
    }

    @GetMapping("/read")
    public String read (Model model) {
        model.addAttribute("information", informationRepository.findAll());
        model.addAttribute("information", informationRepository.findAll());
        return "read";
    }

    @GetMapping("/canedit/{id}")
    public boolean canEditInformation (Model model, @PathVariable("id") String id) {
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        return true;
    }
}
