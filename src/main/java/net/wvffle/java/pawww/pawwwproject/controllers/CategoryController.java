package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.Category;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.StreamSupport;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    Logger log = LoggerFactory.getLogger(Controller.class);
    @GetMapping("addCategory")
    public String category(HttpServletRequest request, Model model) {
        if (request.getRemoteUser() == null) {
            return "index";
        }

        model.addAttribute("category", new Category());

        return "add_category";
    }

    @PostMapping("addCategory")
    public String addCategory(
            @ModelAttribute("category") @Valid Category category
    ) {
        categoryRepository.save(category);
        //wywala bląd bo się gryzie z kategoriami z data.sql jednak po odswiezeniu pare razy dziala ok
        //probowalem zmienic generatedvalue strategy na IDENTITY jednak nie pomoglo

        return "redirect:/";
    }
}
