package com.nakedgardener.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.util.WebUtils.ERROR_STATUS_CODE_ATTRIBUTE;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String FOUR_OH_FOUR_ERROR_MSG = "Like the Naked Gardner's clothes, you appear to be lost.";
    private static final String DEFAULT_ERROR_MSG = "Something went wrong. Please try again.";

    @RequestMapping("/error")
    public String errorWithoutCode(final ModelMap modelMap, HttpServletRequest request) {
        Integer errorCode = errorCode(request);
        modelMap.addAttribute("errorCode", errorCode);
        modelMap.addAttribute("errorMessage", resolveErrorMessage(errorCode));
        return getErrorPath();
    }

    private Integer errorCode(HttpServletRequest request) {
        return (Integer) request.getAttribute(ERROR_STATUS_CODE_ATTRIBUTE);
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

    private String resolveErrorMessage(int errorCode) {
        switch (errorCode) {
            case 404 : return FOUR_OH_FOUR_ERROR_MSG;
            default : return DEFAULT_ERROR_MSG;
        }
    }
}
