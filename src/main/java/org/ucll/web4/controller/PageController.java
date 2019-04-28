package org.ucll.web4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.ucll.web4.dto.UserRegistrationDto;
import org.ucll.web4.service.BlogService;
import org.ucll.web4.service.UserService;
import org.ucll.web4.spring.security.CustomUserDetails;

import javax.validation.Valid;


@Controller
public class PageController {

    private final BlogService blogService;
    private  final UserService userService;

    public PageController(@Autowired BlogService blogService,@Autowired UserService userService){
        this.blogService = blogService;
        this.userService = userService;
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

    //go to register page
    @GetMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView mav = new ModelAndView("register");

        mav.addObject("userRegistrationDto",new UserRegistrationDto());

        return mav;
    }

    //register user
    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationDto userRegistrationDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }

        userService.registerUser(userRegistrationDto);
        return "redirect:login?register";
    }
}
