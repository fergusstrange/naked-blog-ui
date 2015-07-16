package com.nakedgardener.application.blog;

import org.junit.Test;

import java.net.URI;

import static org.fest.assertions.Assertions.assertThat;

public class BlogURLFactoryTest {

    private BlogURLFactory blogURLFactory = new BlogURLFactory("http://localhost:12345", 5);

    @Test
    public void shouldReturnRecentBlogPostsURLWithQueryParametersAdded() throws Exception {
        URI uri = blogURLFactory.mostRecentBlogPostsURL(0);

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/_recent?indexFrom=0&indexTo=4");
    }

    @Test
    public void shouldReturnBlogPostURLWithSlugAppended() throws Exception {
        URI uri = blogURLFactory.blogPostURL("some-post-about-nudity");

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/some-post-about-nudity");
    }
}