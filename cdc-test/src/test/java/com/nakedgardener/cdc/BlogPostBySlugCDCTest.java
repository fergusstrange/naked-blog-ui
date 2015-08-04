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
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CDCTest.class)
public class BlogPostBySlugCDCTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 3456, this);

    @Autowired
    private BlogPostService blogPostService;

    @Pact(state = "A blog post with slug la-la-la", provider = "naked-blog", consumer = "naked-gardener")
    public PactFragment anExistingBlogPost(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("a request for a blog post with slug la-la-la")
                .matchPath("/blog-post/_blogPostSlug/la-la-la")
                .method("GET")
                .willRespondWith()
                .status(200)
                .matchHeader(CONTENT_TYPE, "application/json")
                .body(body())
                .toFragment();
    }

    @Test
    @PactVerification("A blog post with slug la-la-la")
    public void shouldReturnBlogPostThatExistsWithSlug() {
        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("la-la-la");

        assertThat(blogPostResult.isPostExists()).isTrue();
    }

    @Pact(state = "A blog post that doesn't exist", provider = "naked-blog", consumer = "naked-gardener")
    public PactFragment aNonExistentBlogPost(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("a request for a blog post that doesn't exist")
                .matchPath("/blog-post/_blogPostSlug/does-not-exist")
                .method("GET")
                .willRespondWith()
                .status(404)
                .matchHeader(CONTENT_TYPE, "application/json")
                .toFragment();
    }

    @Test
    @PactVerification("A blog post that doesn't exist")
    public void shouldReturn404() throws Exception {
        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("does-not-exist");

        assertThat(blogPostResult.isPostExists()).isFalse();
    }

    @Pact(state = "An error occured within the server", provider = "naked-blog", consumer = "naked-gardener")
    public PactFragment anErrorHasOccuredWithinTheServer(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("a request for a blog post that doesn't exist")
                .matchPath("/blog-post/_blogPostSlug/make-an-error")
                .method("GET")
                .willRespondWith()
                .status(500)
                .matchHeader(CONTENT_TYPE, "application/json")
                .toFragment();
    }

    @Test
    @PactVerification("An error occured within the server")
    public void shouldReturn500() throws Exception {
        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug("make-an-error");

        assertThat(blogPostResult.isPostExists()).isFalse();
    }

    private JSONObject body() {
        BlogPost blogPost = BlogPost.builder()
                .blogPostSlug("la-la-la")
                .build();
        return new JSONObject(blogPost);
    }
}
