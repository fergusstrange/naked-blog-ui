package com.nakedgardener.cdc;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;
import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CDCTest.class)
public class RecentBlogPostsCDCTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 3456, this);

    @Autowired
    private RecentBlogPostsService recentBlogPostsService;

    @Pact(state="blog-post-recent", provider="naked-blog", consumer="naked-gardener")
    public PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("RecentBlogPostsCDCTest test interaction")
                .path("/blog-post/_recent")
                .query("page=0&pageSize=5")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(ImmutableMap.of(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .body(blogPostRestResponseBody())
                .toFragment();
    }

    @Test
    @PactVerification("blog-post-recent")
    public void runTest() {
        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(0);

        assertThat(recentBlogPostsResult.isNoResults()).isFalse();
    }

    private String blogPostRestResponseBody() throws IOException {
        return Resources.toString(getResource("com/nakedgardener/application/blog/blog-posts_rest_response.json"), defaultCharset());
    }
}
