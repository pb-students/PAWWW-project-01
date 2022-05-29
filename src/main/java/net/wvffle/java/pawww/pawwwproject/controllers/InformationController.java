package net.wvffle.java.pawww.pawwwproject.controllers;

import java.util.Optional;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

@Controller
public class InformationController {
    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/information/list/{order}/{desc}")
    public String list (HttpServletRequest request, Model model, @PathVariable("order") String order, @PathVariable("desc") Boolean desc) {
        Sort sort = Sort.by(order);
        if (desc) sort = sort.descending();
        model.addAttribute("informations", informationRepository.findAllByAuthor(userRepository.findByUsername(request.getRemoteUser()), sort));
        return "information/list";
    }
    
    
    @GetMapping("/information/read/{id}")
    public String read (Model model, @PathVariable("id") String id) {
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        model.addAttribute("information", information);
        return "information/read";
    }
}
