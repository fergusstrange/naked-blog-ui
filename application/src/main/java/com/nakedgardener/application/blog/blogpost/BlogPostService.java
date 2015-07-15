package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.BlogURLFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlogPostService {

    private final Logger applicationErrorLog;
    private final BlogURLFactory blogURLFactory;
    private final RestTemplate restTemplate;
    private final BlogPostDTOConverter blogPostDTOConverter;

    @Autowired
    public BlogPostService(Logger applicationErrorLog, BlogURLFactory blogURLFactory, RestTemplate restTemplate, BlogPostDTOConverter blogPostDTOConverter) {
        this.applicationErrorLog = applicationErrorLog;
        this.blogURLFactory = blogURLFactory;
        this.restTemplate = restTemplate;
        this.blogPostDTOConverter = blogPostDTOConverter;
    }
}
