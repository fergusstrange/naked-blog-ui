package com.nakedgardener;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.time.LocalDateTime.now;

@Controller
public class BasicController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String basicPage(final ModelMap modelMap) {
        modelMap.put("someData", now().toString());
        return "home";
    }
}
