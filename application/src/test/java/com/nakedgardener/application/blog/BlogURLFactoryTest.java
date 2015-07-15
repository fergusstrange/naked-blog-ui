package com.nakedgardener.application.blog;

import org.junit.Test;

import java.net.URI;

import static org.fest.assertions.Assertions.assertThat;

public class BlogURLFactoryTest {

    @Test
    public void shouldReturnRecentBlogPostsURLWithQueryParametersAdded() throws Exception {
        URI uri = new BlogURLFactory("http://localhost:12345").mostRecentBlogPostsURL(0, 10);

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/_recent?indexFrom=0&indexTo=10");
    }

    @Test
    public void shouldReturnBlogPostURLWithSlugAppended() throws Exception {
        URI uri = new BlogURLFactory("http://localhost:12345").blogPostURL("some-post-about-nudity");

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/some-post-about-nudity");
    }
}