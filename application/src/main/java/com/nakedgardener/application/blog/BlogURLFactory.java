package com.nakedgardener.application.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
public class BlogURLFactory {

    private final String baseBlogURL;
    private final int blogChunkSize;

    @Autowired
    public BlogURLFactory(@Value("${blog.base.url:http://localhost:8888}") String baseBlogURL,
                          @Value("${blog.chunk.size:5}") int blogChunkSize) {
        this.baseBlogURL = baseBlogURL;
        this.blogChunkSize = blogChunkSize;
    }

    public URI mostRecentBlogPostsURL(int page) {
        return fromHttpUrl(baseBlogURL)
                .path("/blog-post/_recent")
                .queryParam("page", page)
                .queryParam("pageSize", blogChunkSize)
                .build()
                .toUri();
    }

    public URI blogPostURL(String blogPostSlug) {
        return fromHttpUrl(baseBlogURL)
                .path("/blog-post/_blogPostSlug/")
                .pathSegment(blogPostSlug)
                .build()
                .toUri();
    }
}
