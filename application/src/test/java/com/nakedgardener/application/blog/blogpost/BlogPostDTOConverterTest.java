package com.nakedgardener.application.blog.blogpost;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPosts;
import org.junit.Test;

import static com.google.common.io.Resources.getResource;
import static org.fest.assertions.Assertions.assertThat;

public class BlogPostDTOConverterTest {

    private BlogPostDTOConverter blogPostDTOConverter = new BlogPostDTOConverter();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldConvertBlogPostToBlogPostResult() throws Exception {
        BlogPostResult blogPostResult = blogPostDTOConverter.convert(blogPosts());

        assertThat(blogPostResult).isNotNull();
    }

    private BlogPosts blogPosts() throws Exception {
        return objectMapper.readValue(getResource("com/nakedgardener/application/blog/blog-posts_rest_response.json"), BlogPosts.class);
    }
}