package com.nakedgardener.application.blog.recentblogposts;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BlogPostUrlFactory {

    public String blogPostURL(String blogPostSlug) {
        return UriComponentsBuilder
                .fromPath("/blog")
                .pathSegment(blogPostSlug)
                .toUriString();
    }
}
