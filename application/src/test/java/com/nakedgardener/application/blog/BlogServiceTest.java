package com.nakedgardener.application.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {

    @Mock
    private BlogURLFactory blogURLFactory;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BlogService blogService;

    @Test
    public void shouldTryToGetNPlusFourResults() throws Exception {
        blogService.blogPostsByIndex(0);

        verify(blogURLFactory).mostRecentBlogPostsURL(0, 4);
    }
}