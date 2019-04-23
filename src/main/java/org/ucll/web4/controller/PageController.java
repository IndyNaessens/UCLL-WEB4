package org.ucll.web4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.ucll.web4.service.BlogService;
import org.ucll.web4.spring.security.CustomUserDetails;


@Controller
public class PageController {

    private final BlogService blogService;

    public PageController(@Autowired BlogService blogService){
        this.blogService = blogService;
    }


    @GetMapping({"/", "/home"})
    public ModelAndView getHome(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ModelAndView mav = new ModelAndView("home");

        mav.addObject("fullName",userDetails.getFullName());
        mav.addObject("status", userDetails.getStatus());
        mav.addObject("blogs", blogService.getBlogTopics());

        return mav;
    }

    @GetMapping({"/chat"})
    public ModelAndView getChat(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ModelAndView mav = new ModelAndView("chatapp");

        mav.addObject("fullName",userDetails.getFullName());
        mav.addObject("status", userDetails.getStatus());

        return mav;
    }
}
