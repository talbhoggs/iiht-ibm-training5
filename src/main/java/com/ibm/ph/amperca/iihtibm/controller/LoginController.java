package com.ibm.ph.amperca.iihtibm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.ph.amperca.iihtibm.model.User;
import com.ibm.ph.amperca.iihtibm.service.UserService;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login/submit")
    public String submitSignUpForm(@RequestParam("username") String username,
            @RequestParam("password") String password, HttpSession session,
            ModelMap modelMap) {

        return "index";
    }
}
