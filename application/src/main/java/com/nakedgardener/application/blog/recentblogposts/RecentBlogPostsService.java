package com.nakedgardener.application.blog.recentblogposts;

import com.nakedgardener.application.blog.BlogRestServiceURLFactory;
import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult.emptyBlogPostResult;

@Component
public class RecentBlogPostsService {

    private final RestTemplate restTemplate;
    private final BlogRestServiceURLFactory blogRestServiceURLFactory;
    private final RecentBlogPostsDTOConverter recentBlogPostsDTOConverter;
    private final Logger applicationErrorLog;

    @Autowired
    public RecentBlogPostsService(RestTemplate restTemplate, BlogRestServiceURLFactory blogRestServiceURLFactory,
                                  RecentBlogPostsDTOConverter recentBlogPostsDTOConverter, Logger applicationErrorLog) {
        this.restTemplate = restTemplate;
        this.blogRestServiceURLFactory = blogRestServiceURLFactory;
        this.recentBlogPostsDTOConverter = recentBlogPostsDTOConverter;
        this.applicationErrorLog = applicationErrorLog;
    }

    public RecentBlogPostsResult blogPostsByIndex(int fromIndex) {
        try {
            ResponseEntity<BlogPosts> response = restTemplate.getForEntity(url(fromIndex), BlogPosts.class);
            return response.getStatusCode().is2xxSuccessful() ?
                    recentBlogPostsDTOConverter.convert(response.getBody()) :
                    emptyBlogPostResult();
        }
        catch (RestClientException e) {
            applicationErrorLog.error("Error retrieving blog messages.", e);
            return emptyBlogPostResult();
        }
    }

    private URI url(int fromIndex) {
        return blogRestServiceURLFactory.mostRecentBlogPostsURL(fromIndex);
    }
}
