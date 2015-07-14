package com.nakedgardener.application.blog;

import org.junit.Test;

import java.net.URI;

import static org.fest.assertions.Assertions.assertThat;

public class BlogURLFactoryTest {

    @Test
    public void shouldReturnURLWithQueryParametersAdded() throws Exception {
        URI uri = new BlogURLFactory("http://localhost:12345").mostRecentBlogPostsURL(0, 10);

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/_recent?indexFrom=0&indexTo=10");
    }
}