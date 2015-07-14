package com.nakedgardener.application.blog;

import com.nakedgardener.application.blog.domain.BlogPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class BlogService {

    private final RestTemplate restTemplate;
    private final BlogURLFactory blogURLFactory;

    @Autowired
    public BlogService(RestTemplate restTemplate, BlogURLFactory blogURLFactory) {
        this.restTemplate = restTemplate;
        this.blogURLFactory = blogURLFactory;
    }

    public BlogPosts blogPostsByIndex(int fromIndex) {
        URI url = blogURLFactory.blogPostsURL(fromIndex, fromIndex + 4);
        return restTemplate.getForObject(url, BlogPosts.class);
    }
}
