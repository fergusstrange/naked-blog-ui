package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

    @RequestMapping(method = RequestMethod.GET, value = "/contact")
    public String portfolio() {
        return "contact";
    }
}
