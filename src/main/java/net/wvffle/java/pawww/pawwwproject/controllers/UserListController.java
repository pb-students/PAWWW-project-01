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
public class UserListController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userList () {
        return "showusers";
    }
//    @GetMapping("/list")
//    public String user (HttpServletRequest request, Model model) {
//        if (request.getRemoteUser() == null) {
//            return "userlist";
//        }
//
//        Information information = new Information();
//        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("information", information);
//
//        return "wall";
//    }
}
