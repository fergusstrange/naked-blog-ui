package com.nakedgardener.application.blog;

import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
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

import static com.nakedgardener.application.blog.domain.BlogPosts.BlogPost;
import static com.nakedgardener.application.blog.dto.BlogPostsResult.blogPostsResultsBuilder;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class RecentBlogServiceTest {

    @Mock
    private BlogURLFactory blogURLFactory;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BlogPostDTOConverter blogPostDTOConverter;

    @Mock
    private Logger logger;

    @Mock
    private ResponseEntity<BlogPosts> blogPostsResponseEntity;

    @InjectMocks
    private RecentBlogService recentBlogService;

    @Test
    public void shouldTryToGetNPlusFourResults() throws Exception {
        givenRestServiceCalledAndEntityReturnedWithStatus(OK);

        recentBlogService.blogPostsByIndex(0);

        verify(blogURLFactory).mostRecentBlogPostsURL(0, 4);
    }

    @Test
    public void shouldReturnEmptyResultWhenNot200Response() throws Exception {
        givenRestServiceCalledAndEntityReturnedWithStatus(INTERNAL_SERVER_ERROR);

        BlogPostsResult blogPostsResult = recentBlogService.blogPostsByIndex(0);

        assertThat(blogPostsResult.getBlogPosts()).hasSize(0);
        assertThat(blogPostsResult.isNoResults()).isTrue();
    }

    @Test
    public void shouldReturnConvertedObjectFromRestCall() throws Exception {
        givenRestServiceCalledAndEntityReturnedWithStatus(OK);
        given(blogPostDTOConverter.convert(any(BlogPosts.class))).willReturn(blogPostsResult());

        BlogPostsResult blogPostsResult = recentBlogService.blogPostsByIndex(0);

        assertThat(blogPostsResult.isNoResults()).isFalse();
        assertThat(blogPostsResult.getBlogPosts()).hasSize(3);
    }

    @Test
    public void shouldHandleRestClientExceptionAndReturnEmptyResult() throws Exception {
        RestClientException restClientException = new RestClientException("Uh oh!");
        given(restTemplate.getForEntity(any(URI.class), eq(BlogPosts.class)))
                .willThrow(restClientException);

        BlogPostsResult blogPostsResult = recentBlogService.blogPostsByIndex(0);

        assertThat(blogPostsResult.isNoResults()).isTrue();
        assertThat(blogPostsResult.getBlogPosts()).hasSize(0);
        assertThat(blogPostsResult.getNoResultsMessage()).isEqualTo("Like The Naked Gardener, the blog appears to be bare!");

        verify(logger).error("Error retrieving blog messages.", restClientException);
    }

    private BlogPostsResult blogPostsResult() {
        return blogPostsResultsBuilder()
                .blogPosts(asList(new BlogPost(), new BlogPost(), new BlogPost()))
                .build();
    }

    private void givenRestServiceCalledAndEntityReturnedWithStatus(HttpStatus httpStatus) {
        URI testURI = URI.create("/test");
        given(blogURLFactory.mostRecentBlogPostsURL(0, 4)).willReturn(testURI);
        given(restTemplate.getForEntity(testURI, BlogPosts.class)).willReturn(blogPostsResponseEntity);
        given(blogPostsResponseEntity.getStatusCode()).willReturn(httpStatus);
        given(blogPostsResponseEntity.getBody()).willReturn(new BlogPosts());
    }
}