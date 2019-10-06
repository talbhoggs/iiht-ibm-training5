package com.ibm.ph.amperca.iihtibm.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ibm.ph.amperca.iihtibm.model.User;
import com.ibm.ph.amperca.iihtibm.service.SecurityService;
import com.ibm.ph.amperca.iihtibm.service.UserService;

@Controller
public class AccountController {

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

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

  @GetMapping("/accountupdate")
  public String showAccountUpdateForm(@RequestParam(required = false) String error, Model model) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUserName(auth.getName());

    model.addAttribute("user", user);
    return "accountupdate";
  }

  @PostMapping("/accountupdate/submit")
  public String showAccountUpdateForm(@ModelAttribute("user") @Valid User user,
      BindingResult result, Model model) {

    if (result.hasErrors()) {
      return "accountupdate";
    }

    userService.updateUser(user);

    SecurityContextHolder.getContext().setAuthentication(null);


    return "redirect:/login?accountUpdate=true";
  }



}
