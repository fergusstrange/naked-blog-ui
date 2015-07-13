package com.nakedgardener.application.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class NavigationItemFactory {

    private final Boolean blogOn;

    @Autowired
    public NavigationItemFactory(@Value("${blog.on:false}") Boolean blogOn) {
        this.blogOn = blogOn;
    }

    public List<NavigationItem> create(String requestURI) {
        return blogOn ?
                navigationItemsWithBlog(requestURI) :
                navigationItems(requestURI);
    }

    private List<NavigationItem> navigationItemsWithBlog(String requestURI) {
        return asList(
                navigationItem("/", "Services", requestURI),
                navigationItem("/curriculum-vitae", "Curriculum Vitae", requestURI),
                navigationItem("/blog", "Blog", requestURI),
                navigationItem("/contact", "Contact", requestURI)
        );
    }

    private List<NavigationItem> navigationItems(String requestURI) {
        return asList(
                navigationItem("/", "Services", requestURI),
                navigationItem("/curriculum-vitae", "Curriculum Vitae", requestURI),
                navigationItem("/contact", "Contact", requestURI)
        );
    }

    private NavigationItem navigationItem(String uri, String name, String requestURI) {
        return new NavigationItem(uri, name, uri.equals(requestURI));
    }
}