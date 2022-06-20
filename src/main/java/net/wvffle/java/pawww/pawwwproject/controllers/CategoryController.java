package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.Category;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    Logger log = LoggerFactory.getLogger(Controller.class);
    @GetMapping("categories/new")
    public String category(HttpServletRequest request, Model model) {
        if (request.getRemoteUser() == null) {
            return "index";
        }

        model.addAttribute("category", new Category());

        return "add_category";
    }

    @PostMapping("categories/new")
    public String addCategory(
            @ModelAttribute("category") @Valid Category category
    ) {
        categoryRepository.save(category);

        return "redirect:/";
    }
}
