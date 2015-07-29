package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServicesController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String services() {
        return "services";
    }
}
