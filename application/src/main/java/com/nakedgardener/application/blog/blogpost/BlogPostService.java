package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.BlogRestServiceURLFactory;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.nakedgardener.application.blog.blogpost.dto.BlogPostResult.emptyBlogPostResult;

@Component
public class BlogPostService {

    private final Logger applicationErrorLog;
    private final BlogRestServiceURLFactory blogRestServiceURLFactory;
    private final RestTemplate restTemplate;
    private final BlogPostDTOConverter blogPostDTOConverter;

    @Autowired
    public BlogPostService(Logger applicationErrorLog, BlogRestServiceURLFactory blogRestServiceURLFactory, RestTemplate restTemplate, BlogPostDTOConverter blogPostDTOConverter) {
        this.applicationErrorLog = applicationErrorLog;
        this.blogRestServiceURLFactory = blogRestServiceURLFactory;
        this.restTemplate = restTemplate;
        this.blogPostDTOConverter = blogPostDTOConverter;
    }

    public BlogPostResult blogPostByBlogPostSlug(String blogPostSlug) {
        try {
            URI blogPostURL = blogRestServiceURLFactory.blogPostURL(blogPostSlug);
            ResponseEntity<BlogPost> blogPostResponseEntity = restTemplate.getForEntity(blogPostURL, BlogPost.class);
            return blogPostResponseEntity.getStatusCode().is2xxSuccessful() ?
                    blogPostDTOConverter.convert(blogPostResponseEntity.getBody()) :
                    emptyBlogPostResult();
        }
        catch(RestClientException e) {
            applicationErrorLog.error(e.getMessage(), e);
            return emptyBlogPostResult();
        }
    }
}
