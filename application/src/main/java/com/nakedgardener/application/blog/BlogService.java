package com.nakedgardener.application.blog;

import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.nakedgardener.application.blog.dto.BlogPostsResult.emptyBlogPostResult;

@Component
public class BlogService {

    private final RestTemplate restTemplate;
    private final BlogURLFactory blogURLFactory;
    private final BlogPostDTOConverter blogPostDTOConverter;

    @Autowired
    public BlogService(RestTemplate restTemplate, BlogURLFactory blogURLFactory, BlogPostDTOConverter blogPostDTOConverter) {
        this.restTemplate = restTemplate;
        this.blogURLFactory = blogURLFactory;
        this.blogPostDTOConverter = blogPostDTOConverter;
    }

    public BlogPostsResult blogPostsByIndex(int fromIndex) {
        try {
            ResponseEntity<BlogPosts> response = restTemplate.getForEntity(url(fromIndex), BlogPosts.class);
            return response.getStatusCode().is2xxSuccessful() ?
                    blogPostDTOConverter.convert(response.getBody()) :
                    emptyBlogPostResult();
        }
        catch (RestClientException e) {
            return emptyBlogPostResult();
        }
    }

    private URI url(int fromIndex) {
        return blogURLFactory.mostRecentBlogPostsURL(fromIndex, fromIndex + 4);
    }
}
