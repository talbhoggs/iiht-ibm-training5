package com.ibm.ph.amperca.iihtibm.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.ibm.ph.amperca.iihtibm.model.User;
import com.ibm.ph.amperca.iihtibm.service.UserService;

@Controller
public class SignUpController {

  @Autowired
  private UserService userService;

  @GetMapping("/signup")
  public String showSignUpForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    return "signup";
  }

  @PostMapping("/signup/submit")
  public String submitSignUpForm(@ModelAttribute("user") @Valid User user, BindingResult result,
      Model model) {

    if (result.hasErrors()) {
      return "signup";
    }

    userService.saveUser(user);

    return "redirect:/login";
  }

}
