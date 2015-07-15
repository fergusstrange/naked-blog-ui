package com.nakedgardener.application.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
public class BlogURLFactory {

    private final String baseBlogURL;

    @Autowired
    public BlogURLFactory(@Value("${blog.base.url:http://localhost:8888}") String baseBlogURL) {
        this.baseBlogURL = baseBlogURL;
    }

    public URI mostRecentBlogPostsURL(int fromIndex, int toIndex) {
        return fromHttpUrl(baseBlogURL)
                .path("/blog-post/_recent")
                .queryParam("indexFrom", fromIndex)
                .queryParam("indexTo", toIndex)
                .build()
                .toUri();
    }

    public URI blogPostURL(String blogPostSlug) {
        return fromHttpUrl(baseBlogURL)
                .path("/blog-post/{blogPostSlug}")
                .pathSegment(blogPostSlug)
                .build()
                .toUri();
    }
}
