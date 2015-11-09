package com.nakedgardener.application.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
public class BlogRestServiceURLFactory {

    private final String baseBlogURL;

    @Autowired
    public BlogRestServiceURLFactory(@Value("${blog.base.url:http://localhost:8888}") String baseBlogURL) {
        this.baseBlogURL = baseBlogURL;
    }

    public URI blogPostURL(String blogPostSlug) {
        return fromHttpUrl(baseBlogURL)
                .path("/blog-post/_blogPostSlug/")
                .pathSegment(blogPostSlug)
                .build()
                .toUri();
    }
}
