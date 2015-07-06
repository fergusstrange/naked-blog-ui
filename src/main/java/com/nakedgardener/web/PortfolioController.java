package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PortfolioController {

    @RequestMapping(method = RequestMethod.GET, value = "/portfolio")
    public String portfolio() {
        return "portfolio";
    }
}
