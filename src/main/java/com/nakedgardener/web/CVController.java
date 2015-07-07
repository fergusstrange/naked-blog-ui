package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class CVController {

    @RequestMapping(method = GET, value = {"/curriculum-vitae", "/CV", "/cv"})
    public String portfolio() {
        return "curriculum-vitae";
    }
}
