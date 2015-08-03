package com.nakedgardener.cdc;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.nakedgardener.application.blog.blogpost.BlogPostService;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CDCTest.class)
public class BlogPostBySlugCDCTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 3456, this);

    @Autowired
    private BlogPostService blogPostService;

    @Pact(state = "A blog post with slug la-la-la", provider = "naked-blog", consumer = "naked-gardener")
    public PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("a request for a blog post with slug la-la-la")
                .matchPath("/blog-post/_blogPostSlug/la-la-la")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body())
                .toFragment();
    }

    @Test
    @PactVerification("A blog post with slug la-la-la")
    public void runTest() {
        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("la-la-la");

        assertThat(blogPostResult.isPostExists()).isTrue();
    }

    private JSONObject body() {
        BlogPost blogPost = BlogPost.builder()
                .blogPostSlug("la-la-la")
                .build();
        return new JSONObject(blogPost);
    }
}
