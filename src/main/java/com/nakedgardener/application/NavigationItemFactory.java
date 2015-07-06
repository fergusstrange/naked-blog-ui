package com.nakedgardener.application;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class NavigationItemFactory {

    public List<NavigationItem> create(String requestURI) {
        return asList(
                navigationItem("/", "Home", requestURI),
                navigationItem("/portfolio", "Portfolio", requestURI),
                navigationItem("/testimonials", "Testimonials", requestURI),
                navigationItem("/curriculum-vitae", "Curriculum Vitae", requestURI),
                navigationItem("/contact", "Contact", requestURI)
        );
    }

    private NavigationItem navigationItem(String uri, String name, String requestURI) {
        return new NavigationItem(uri, name, uri.equals(requestURI));
    }
}