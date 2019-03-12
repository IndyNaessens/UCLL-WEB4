package org.ucll.web4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController {

    @GetMapping({"/", "/home"})
    public ModelAndView getIndex() {
        return new ModelAndView("chatapp");
    }
}
