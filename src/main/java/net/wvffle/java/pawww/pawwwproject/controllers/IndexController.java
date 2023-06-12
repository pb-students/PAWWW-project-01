package net.wvffle.java.pawww.pawwwproject.controllers;

import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import net.wvffle.java.pawww.pawwwproject.db.information.Information;
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
    public String index () {
        return "index";
    }
}

