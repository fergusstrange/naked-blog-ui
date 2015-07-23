package com.nakedgardener.application.blog.recentblogposts;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BlogPostUrlFactoryTest {

    @Test
    public void shouldReturnBlogPostURL() throws Exception {
        String url = new BlogPostUrlFactory().blogPostURL("a-slug");

        assertThat(url).isEqualTo("/blog/a-slug");
    }
}