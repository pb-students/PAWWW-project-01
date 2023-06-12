package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.Category;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/categories/new")
    public ResponseEntity<Category> addCategory(
            @ModelAttribute @Valid Category category,
            BindingResult bindingResult
    ) {
        if (categoryRepository.existsByName(category.getName())) {
            bindingResult.addError(new FieldError("category", "name", "Category already exists"));
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }
}
