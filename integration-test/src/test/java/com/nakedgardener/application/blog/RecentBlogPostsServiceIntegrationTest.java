package com.nakedgardener.application.blog;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.io.Resources;
import com.nakedgardener.IntegrationTestApplication;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestApplication.class)
public class RecentBlogPostsServiceIntegrationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(3456);

    @Autowired
    private RecentBlogPostsService recentBlogPostsService;

    @Test
    public void shouldReturnBlogPosts() throws Exception {
        stubFor(get(
                urlEqualTo("/blog-post/_recent?page=0&pageSize=5"))
                .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(restResponse())
                ));

        RecentBlogPostsResult blogPosts = recentBlogPostsService.blogPostsByIndex(0);

        assertThat(blogPosts).isNotNull();
        assertThat(blogPosts.isNoResults()).isFalse();
        assertThat(blogPosts.getBlogPostPreviews()).hasSize(5);
    }

    private String restResponse() throws IOException {
        return Resources.toString(getResource("com/nakedgardener/application/blog/blog-posts_rest_response.json"), defaultCharset());
    }
}