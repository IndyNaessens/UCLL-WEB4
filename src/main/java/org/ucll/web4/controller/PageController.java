package org.ucll.web4.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.ucll.web4.spring.security.CustomUserDetails;


@Controller
public class PageController {

    @GetMapping({"/", "/home"})
    public ModelAndView getIndex(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ModelAndView mav = new ModelAndView("chatapp");

        mav.addObject("fullName",userDetails.getFullName());
        mav.addObject("status", userDetails.getStatus());

        return mav;
    }
}
