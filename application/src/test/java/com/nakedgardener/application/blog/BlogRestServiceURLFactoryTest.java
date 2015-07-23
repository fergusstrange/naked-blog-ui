package com.nakedgardener.application.blog;

import org.junit.Test;

import java.net.URI;

import static org.fest.assertions.Assertions.assertThat;

public class BlogRestServiceURLFactoryTest {

    private BlogRestServiceURLFactory blogRestServiceURLFactory = new BlogRestServiceURLFactory("http://localhost:12345", 5);

    @Test
    public void shouldReturnRecentBlogPostsURLWithQueryParametersAdded() throws Exception {
        URI uri = blogRestServiceURLFactory.mostRecentBlogPostsURL(0);

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/_recent?page=0&pageSize=5");
    }

    @Test
    public void shouldReturnBlogPostURLWithSlugAppended() throws Exception {
        URI uri = blogRestServiceURLFactory.blogPostURL("some-post-about-nudity");

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-post/_blogPostSlug/some-post-about-nudity");
    }
}