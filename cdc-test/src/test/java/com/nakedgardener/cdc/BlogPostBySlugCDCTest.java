package com.nakedgardener.cdc;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.google.common.io.Resources;
import com.nakedgardener.application.blog.blogpost.BlogPostService;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CDCTest.class)
public class BlogPostBySlugCDCTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 3456, this);

    @Autowired
    private BlogPostService blogPostService;

    @Pact(state="blog-post-by-slug", provider="naked-blog", consumer="naked-gardener")
    public PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("BlogPostBySlugCDCTest test interaction")
                .matchPath("/blog-post/_blogPostSlug/.+")
                .method("GET")
                .willRespondWith()
                .status(200)
                .matchHeader("Content-Type", "application/json;charset=UTF-8")
                .body(blogPostRestResponseBody())
                .toFragment();
    }

    @Test
    @PactVerification("blog-post-by-slug")
    public void runTest() {
        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("la-la-la");

        assertThat(blogPostResult.isPostExists()).isTrue();
    }

    private String blogPostRestResponseBody() throws IOException {
        return Resources.toString(getResource("com/nakedgardener/application/blog/blog-post_by_slug_rest_response.json"), defaultCharset());
    }
}
