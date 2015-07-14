package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String homepage() {
        return "services";
    }
}
