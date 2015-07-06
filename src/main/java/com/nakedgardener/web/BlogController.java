package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BlogController {

    @RequestMapping(method = GET, value = "/blog")
    public String blog() {
        return "blog";
    }
}
