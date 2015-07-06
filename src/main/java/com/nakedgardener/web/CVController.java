package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CVController {

    @RequestMapping(method = RequestMethod.GET, value = "/curriculum-vitae")
    public String portfolio() {
        return "curriculum-vitae";
    }
}
