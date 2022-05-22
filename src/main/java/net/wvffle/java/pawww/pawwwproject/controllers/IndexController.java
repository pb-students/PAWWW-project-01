package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String index (HttpServletRequest request, Model model) {
        if (request.getRemoteUser() == null) {
            return "index";
        }

        model.addAttribute("categories", categoryRepository.findAll());
        return "wall";
    }
}
