package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {

    @RequestMapping(method = RequestMethod.GET, value = "/blog")
    public String blog() {
        return "blog";
    }
}