package com.nakedgardener.cdc;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;
import com.nakedgardener.application.blog.domain.BlogPost;
import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CDCTest.class)
public class RecentBlogPostCDCTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 3456, this);

    @Autowired
    private RecentBlogPostsService recentBlogPostsService;

    @Pact(state = "Two recent blog posts", provider = "naked-blog", consumer = "naked-gardener")
    public PactFragment twoRecentBlogPosts(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) throws Exception {
        return builder
                .uponReceiving("a request for the latest five blog posts")
                .matchPath("/blog-post/_recent")
                .query("page=0&pageSize=5")
                .method("GET")
                .willRespondWith()
                .status(200)
                .matchHeader(CONTENT_TYPE, "application/json")
                .body(body())
                .toFragment();
    }

    @Test
    @PactVerification("Two recent blog posts")
    public void shouldReturnTwoRecentBlogPostsSuccessfully() {
        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(0);

        assertThat(recentBlogPostsResult.isNoResults()).isFalse();
        assertThat(recentBlogPostsResult.getBlogPostPreviews()).hasSize(2);
    }

    private JSONObject body() {
        BlogPosts blogPosts = BlogPosts.builder()
                .blogPosts(asList(blogPostWithSlug("la-la-la"), blogPostWithSlug("na-na-na")))
                .build();
        return new JSONObject(blogPosts);
    }

    private BlogPost blogPostWithSlug(String blogPostSlug) {
        return BlogPost.builder().
                        blogPostSlug(blogPostSlug)
                        .build();
    }
}
