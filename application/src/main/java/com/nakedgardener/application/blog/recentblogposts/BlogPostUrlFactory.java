package com.nakedgardener.application.blog.recentblogposts;

import org.springframework.stereotype.Component;

@Component
public class BlogPostUrlFactory {

    public String blogPostURL(String blogPostSlug) {
        return String.format("/blog/%s", blogPostSlug);
    }
}
