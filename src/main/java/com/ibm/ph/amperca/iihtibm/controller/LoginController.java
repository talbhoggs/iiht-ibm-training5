package com.ibm.ph.amperca.iihtibm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ibm.ph.amperca.iihtibm.model.User;
import com.ibm.ph.amperca.iihtibm.service.UserService;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "login";
    }

}
