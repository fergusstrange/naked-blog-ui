package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestimonialsController {

    @RequestMapping(method = RequestMethod.GET, value = "/testimonials")
    public String portfolio() {
        return "testimonials";
    }
}
