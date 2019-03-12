package org.ucll.web4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    public AuthController() {
    }

    @GetMapping("/login")
    public ModelAndView getLogin(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView("login");

        if (error != null) mav.addObject("loginError", "True");
        if (logout != null) mav.addObject("logoutSuccess", "True");

        return mav;
    }

    @PostMapping("/login-success")
    public RedirectView loginSuccess() {
        return new RedirectView("/home");
    }

    @PostMapping("/login-failed")
    public RedirectView loginFailed() {
        return new RedirectView("/login?error");
    }

    @GetMapping("/logout-success")
    public RedirectView logoutSuccessGet() {
        return new RedirectView("/login?logout");
    }
}
