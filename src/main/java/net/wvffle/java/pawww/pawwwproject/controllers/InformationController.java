package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;
import net.wvffle.java.pawww.pawwwproject.db.user.User;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.data.domain.Sort;

@Controller
public class InformationController {
    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/information/list")
    public String list (HttpServletRequest request, Model model) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        Sort sort = Sort.by(user.getSortOrder());
        if (user.getSortDesc()) sort = sort.descending();
        model.addAttribute("informations", informationRepository.findAllByAuthor(user, sort));
        return "information/list";
    }
    
    @GetMapping("/information/list/{order}/{desc}")
    public String listOrdered (HttpServletRequest request, Model model, @PathVariable("order") String order, @PathVariable("desc") Boolean desc) {
        User user = userRepository.findByUsername(request.getRemoteUser());
        user.setSortOrder(order);
        user.setSortDesc(desc);
        Sort sort = Sort.by(order);
        if (desc) sort = sort.descending();
        model.addAttribute("informations", informationRepository.findAllByAuthor(user, sort));
        return "information/list";
    }
    
    
    @GetMapping("/information/read/{id}")
    public String read (Model model, @PathVariable("id") String id) {
        Information information = informationRepository.findById(Integer.parseInt(id)).orElse(new Information());
        model.addAttribute("information", information);
        return "information/read";
    }
}
