package com.nakedgardener.application.navigation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class NavigationItemFactory {

    @Value("${blog.on:false}")
    private Boolean blogOn;

    public List<NavigationItem> create(String requestURI) {
        List<NavigationItem> navigationItems = asList(
                navigationItem("/", "Services", requestURI),
                navigationItem("/curriculum-vitae", "Curriculum Vitae", requestURI),
                navigationItem("/contact", "Contact", requestURI)
        );

        if(blogOn) {
            navigationItems.add(2, navigationItem("/blog", "Blog", requestURI));
        }

        return navigationItems;
    }

    private NavigationItem navigationItem(String uri, String name, String requestURI) {
        return new NavigationItem(uri, name, uri.equals(requestURI));
    }
}