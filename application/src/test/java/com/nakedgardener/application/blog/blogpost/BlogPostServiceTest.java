package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.BlogRestServiceURLFactory;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class BlogPostServiceTest {
    
    @Mock
    private Logger applicationErrorLog;
    @Mock
    private BlogRestServiceURLFactory blogRestServiceURLFactory;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private BlogPostDTOConverter blogPostDTOConverter;

    @Mock
    private ResponseEntity<BlogPost> responseEntity;
    @Mock
    private BlogPost blogPost;

    @InjectMocks
    private BlogPostService blogPostService;

    @Test
    public void shouldReturnPopulatedBlogPostResult() throws Exception {
        givenRestCallWillRespondWithStatus(OK);

        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("some-post-that-is-not-interesting");

        assertThat(blogPostResult).isNotNull();
        assertThat(blogPostResult.isPostExists()).isTrue();
        assertThat(blogPostResult.getTitle()).isEqualTo("Title");
        assertThat(blogPostResult.getPost()).isEqualTo("Some post content");
    }

    @Test
    public void shouldReturnEmptyResultWhenStatusCodeNotInSuccessRange() throws Exception {
        givenRestCallWillRespondWithStatus(INTERNAL_SERVER_ERROR);

        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("absolutely-anything");

        assertThat(blogPostResult.isPostExists()).isFalse();
        assertThat(blogPostResult.getPostNotExistsMessage()).isEqualTo("Sorry, this post doesn't exist.");
    }

    @Test
    public void shouldHandleRestClientExceptionAndReturnEmptyResult() throws Exception {
        RestClientException restClientException = new RestClientException("Something gone wrong.");
        given(restTemplate.getForEntity(any(URI.class), eq(BlogPost.class))).willThrow(restClientException);

        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("dip-dip-chicken");

        assertThat(blogPostResult.isPostExists()).isFalse();
        verify(applicationErrorLog).error(restClientException.getMessage(), restClientException);
    }

    private void givenRestCallWillRespondWithStatus(HttpStatus httpStatus) {
        given(restTemplate.getForEntity(any(URI.class), eq(BlogPost.class))).willReturn(responseEntity);
        given(responseEntity.getStatusCode()).willReturn(httpStatus);
        given(responseEntity.getBody()).willReturn(blogPost);
        given(blogPostDTOConverter.convert(blogPost)).willReturn(aBlogPost());
    }

    private BlogPostResult aBlogPost() {
        return BlogPostResult.builder()
                .postExists(true)
                .title("Title")
                .post("Some post content")
                .build();
    }
}