package com.nakedgardener.cdc;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.google.common.collect.ImmutableMap;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CDCTest.class)
public class RecentBlogPostsCDCTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 3456, this);

    @Autowired
    private RecentBlogPostsService recentBlogPostsService;

    @Pact(state="test", provider="naked-blog", consumer="naked-gardener")
    public PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        return builder
                .uponReceiving("RecentBlogPostsCDCTest test interaction")
                .path("/blog-post/_recent")
                .query("page=0&pageSize=5")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(ImmutableMap.of("Content-Type", APPLICATION_JSON_VALUE))
                .body("{}")
                .toFragment();
    }

    @Test
    @PactVerification("test")
    public void runTest() {
        recentBlogPostsService.blogPostsByIndex(0);
    }
}
