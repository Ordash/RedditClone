package com.greenfox.redditclone.controllers;

import com.greenfox.redditclone.repositories.entities.User;
import com.greenfox.redditclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"","/"})
    public String main(Model model){
        model.addAttribute("newuser", new User());
        return "userlogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/posts";
    }
}
