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

    public URI blogPostsURL(int fromIndex, int toIndex) {
        return fromHttpUrl(baseBlogURL)
                .path("/blog-posts")
                .queryParam("fromIndex", fromIndex)
                .queryParam("toIndex", toIndex)
                .build()
                .toUri();
    }
}
