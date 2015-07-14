package com.nakedgardener.application.blog;

import org.junit.Test;

import java.net.URI;

import static org.fest.assertions.Assertions.assertThat;

public class BlogURLFactoryTest {

    @Test
    public void should() throws Exception {
        URI uri = new BlogURLFactory("http://localhost:12345").blogPostsURL(0, 10);

        assertThat(uri.toString()).isEqualTo("http://localhost:12345/blog-posts?ndexFrom=0&indexTo=10");
    }
}