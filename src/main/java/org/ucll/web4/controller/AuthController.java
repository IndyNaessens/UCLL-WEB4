package org.ucll.web4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.ucll.web4.dto.UserRegistrationDto;
import org.ucll.web4.service.BlogService;
import org.ucll.web4.service.UserService;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final BlogService blogService;

    public AuthController(@Autowired BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout,
                                 @RequestParam(value = "register", required = false) String register) {
        ModelAndView mav = new ModelAndView("login");

        if (error != null) mav.addObject("loginError", "True");
        if (logout != null) mav.addObject("logoutSuccess", "True");
        if (register != null) mav.addObject("registerSuccess", "True");
        mav.addObject("blogs", blogService.getBlogTopics());

        return mav;
    }

    @PostMapping("/login-success")
    public RedirectView loginSuccess() {
        return new RedirectView("/chat");
    }

    @PostMapping("/login-failed")
    public RedirectView loginFailed() {
        return new RedirectView("/login?error");
    }

    @GetMapping("/logout-success")
    public RedirectView logoutSuccess() {
        return new RedirectView("/login?logout");
    }
}
