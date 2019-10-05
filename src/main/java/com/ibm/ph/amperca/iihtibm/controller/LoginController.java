package com.ibm.ph.amperca.iihtibm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ibm.ph.amperca.iihtibm.model.User;
import com.ibm.ph.amperca.iihtibm.service.SecurityService;
import com.ibm.ph.amperca.iihtibm.service.UserService;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @GetMapping("/login")
  public String showLoginForm(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    System.out.println("++++ show login form");

    User user = new User();
    model.addAttribute("user", user);
    return "login";
  }

  @PostMapping("/login/submit")
  public String submitSignUpForm(@RequestParam("username") String username,
      @RequestParam("password") String password, HttpSession session, ModelMap modelMap) {

    if (username.trim().isEmpty() || password.trim().isEmpty()) {
      return "redirect:/login?error=true";
    }

    boolean isAuthenticated = securityService.authenticate(username, password);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (isAuthenticated) {
      return "redirect:/main";
    }

    return "redirect:/login?error=true";
  }

  @GetMapping("/main")
  public String welcomePage() {
    return "main";
  }

  @GetMapping("/admin/main")
  public String adminPage() {
    return "admin";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      auth.setAuthenticated(false);
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
  }

  @GetMapping("/access-denied")
  public String accessDeniedPage(HttpServletRequest request, HttpServletResponse response) {
    return "redirect:/error";
  }

  @GetMapping("/about")
  public String aboutPage(HttpServletRequest request, HttpServletResponse response) {
    return "about";
  }
}
