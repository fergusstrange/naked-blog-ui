package com.nakedgardener.application.blog;

import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsDTOConverter;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import com.nakedgardener.application.blog.recentblogposts.dto.BlogPostPreview;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
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

import static com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult.blogPostsResultsBuilder;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class RecentBlogPostsServiceTest {

    @Mock
    private BlogURLFactory blogURLFactory;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RecentBlogPostsDTOConverter recentBlogPostsDTOConverter;

    @Mock
    private Logger logger;

    @Mock
    private ResponseEntity<BlogPosts> blogPostsResponseEntity;

    @InjectMocks
    private RecentBlogPostsService recentBlogPostsService;

    @Test
    public void shouldTryToGetNPlusFourResults() throws Exception {
        givenRestServiceCalledAndEntityReturnedWithStatus(OK);

        recentBlogPostsService.blogPostsByIndex(0);

        verify(blogURLFactory).mostRecentBlogPostsURL(0, 4);
    }

    @Test
    public void shouldReturnEmptyResultWhenNot200Response() throws Exception {
        givenRestServiceCalledAndEntityReturnedWithStatus(INTERNAL_SERVER_ERROR);

        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(0);

        assertThat(recentBlogPostsResult.getBlogPostPreviews()).hasSize(0);
        assertThat(recentBlogPostsResult.isNoResults()).isTrue();
    }

    @Test
    public void shouldReturnConvertedObjectFromRestCall() throws Exception {
        givenRestServiceCalledAndEntityReturnedWithStatus(OK);
        given(recentBlogPostsDTOConverter.convert(any(BlogPosts.class))).willReturn(blogPostsResult());

        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(0);

        assertThat(recentBlogPostsResult.isNoResults()).isFalse();
        assertThat(recentBlogPostsResult.getBlogPostPreviews()).hasSize(3);
    }

    @Test
    public void shouldHandleRestClientExceptionAndReturnEmptyResult() throws Exception {
        RestClientException restClientException = new RestClientException("Uh oh!");
        given(restTemplate.getForEntity(any(URI.class), eq(BlogPosts.class)))
                .willThrow(restClientException);

        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(0);

        assertThat(recentBlogPostsResult.isNoResults()).isTrue();
        assertThat(recentBlogPostsResult.getBlogPostPreviews()).hasSize(0);
        assertThat(recentBlogPostsResult.getNoResultsMessage()).isEqualTo("Like The Naked Gardener, the blog appears to be bare!");

        verify(logger).error("Error retrieving blog messages.", restClientException);
    }

    private RecentBlogPostsResult blogPostsResult() {
        return blogPostsResultsBuilder()
                .blogPostPreviews(asList(emptyPreview(), emptyPreview(), emptyPreview()))
                .build();
    }

    private BlogPostPreview emptyPreview() {
        return BlogPostPreview.blogPostPreviewBuilder().build();
    }

    private void givenRestServiceCalledAndEntityReturnedWithStatus(HttpStatus httpStatus) {
        URI testURI = URI.create("/test");
        given(blogURLFactory.mostRecentBlogPostsURL(0, 4)).willReturn(testURI);
        given(restTemplate.getForEntity(testURI, BlogPosts.class)).willReturn(blogPostsResponseEntity);
        given(blogPostsResponseEntity.getStatusCode()).willReturn(httpStatus);
        given(blogPostsResponseEntity.getBody()).willReturn(new BlogPosts());
    }
}