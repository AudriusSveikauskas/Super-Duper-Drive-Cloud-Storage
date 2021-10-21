package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signup() {
        return "signup";
    }

    @PostMapping()
    public String signup(@ModelAttribute User user, Model model) {
        if (!userService.isUsernameAvailable(user.getUsername())) {
            model.addAttribute("signupStatusError", "Error! Username already exists.");
        } else if (userService.isUsernameAvailable(user.getUsername()) && userService.addNewUser(user) < 1) {
            model.addAttribute("signupStatusError", "Error! We are unable to successfully complete your registration at this time. Try again.");
        } else {
            model.addAttribute("signupStatusSuccess", true);
        }
        return "signup";
    }

}
