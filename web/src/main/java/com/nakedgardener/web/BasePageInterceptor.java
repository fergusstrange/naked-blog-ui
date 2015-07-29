package com.nakedgardener.web;

import com.nakedgardener.application.navigation.NavigationItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.time.Year.now;

@Component
public class BasePageInterceptor extends HandlerInterceptorAdapter {

    private NavigationItemFactory navigationItemFactory;

    @Autowired
    public BasePageInterceptor(NavigationItemFactory navigationItemFactory) {
        this.navigationItemFactory = navigationItemFactory;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.getModelMap().computeIfAbsent("pageTitle", value -> "The Naked Gardener | Cameron Strange - Providing professional gardening services in the villages around Saffron Walden.");
        modelAndView.getModelMap().computeIfAbsent("pageDescription", value -> "Garden maintenance run by Cameron Strange around Saffron Walden, Thaxted and Great Bardfield. Services include hedge cutting, strimming and weeding.");
        modelAndView.getModel().computeIfAbsent("pageTags", value -> "Garden Maintenance Saffron Walden Thaxted Great Bardfield Finchingfield Cameron Strange Hedge Cutting Lawn Mowing Strimming Weeding Waste Clearance");

        modelAndView.addObject("pageYear", now().getValue());
        modelAndView.addObject("navigationItems", navigationItemFactory.create(request.getRequestURI()));
    }
}
