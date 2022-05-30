package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import net.wvffle.java.pawww.pawwwproject.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String index (HttpServletRequest request, Model model) {
        if (request.getRemoteUser() == null) {
            return "index";
        }

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("information", new Information());

        return "wall";
    }
}