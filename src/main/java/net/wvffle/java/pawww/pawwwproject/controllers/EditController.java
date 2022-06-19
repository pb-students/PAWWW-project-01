package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class EditController {
    @Autowired
    private InformationRepository informationRepository;


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/edit/{id}")
    public String canEditInformation(Model model, @PathVariable("id") String id) {
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        model.addAttribute("information", information);
        return "editinformation";
    }

    @PostMapping("/update")
    public String register (@ModelAttribute("information") @Valid Information information, Model model) {
//        informationRepository.delete(informationRepository.findById(information.getId()));
        return "redirect:/";
    }
}
