package com.nakedgardener.application.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BlogURLFactory blogURLFactory;

    @InjectMocks
    private BlogService blogService;

    @Test
    public void shouldReturnBogPosts() throws Exception {
        blogService.blogPostsByIndex(0);
    }
}