package net.wvffle.java.pawww.pawwwproject.controllers;

import java.util.Optional;
import net.wvffle.java.pawww.pawwwproject.db.category.CategoryRepository;
import net.wvffle.java.pawww.pawwwproject.db.information.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import net.wvffle.java.pawww.pawwwproject.db.information.InformationRepository;

@Controller
public class ReadController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InformationRepository informationRepository;

    @GetMapping("/read")
    public String read (HttpServletRequest request, Model model) {
        if (request.getRemoteUser() == null || request.getParameter("id") == null) {
            return "index";
        }
        Information information = informationRepository.findById(Integer.parseInt(request.getParameter("id"))).orElse(new Information());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("information", information);
        return "read";
    }
}
