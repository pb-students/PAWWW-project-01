package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.Category;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Iterator;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("categories/new")
    public String category(Model model) {
        model.addAttribute("category", new Category());

        return "add_category";
    }

    @PostMapping("categories/new")
    public String addCategory(
            @ModelAttribute("category") @Valid Category category, Model model
    ) {
        Iterator<Category> categories = categoryRepository.findAll().iterator();
        model.addAttribute("category", new Category());

        while (categories.hasNext()) {
            if (categories.next().getName().equals(category.getName())) {
                model.addAttribute("categoryAddedSuccessful", false);
                return "add_category";
            }
        }

        model.addAttribute("categoryAddedSuccessful", true);
        categoryRepository.save(category);

        return "add_category";
    }
}
