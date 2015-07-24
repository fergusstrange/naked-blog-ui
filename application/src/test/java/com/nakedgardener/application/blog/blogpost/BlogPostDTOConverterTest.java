package com.nakedgardener.application.blog.blogpost;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.junit.Test;

import static com.google.common.io.Resources.getResource;
import static org.fest.assertions.Assertions.assertThat;

public class BlogPostDTOConverterTest {

    private BlogPostDTOConverter blogPostDTOConverter = new BlogPostDTOConverter();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldConvertBlogPostToBlogPostResult() throws Exception {
        BlogPostResult blogPostResult = blogPostDTOConverter.convert(blogPost());

        assertThat(blogPostResult).isNotNull();
        assertThat(blogPostResult.getTitle()).isEqualTo("A Test Blog");
        assertThat(blogPostResult.getPost()).isEqualTo("Some content in the blog!");
        assertThat(blogPostResult.getPostDate()).isEqualTo("02-02-2014 15:09");
        assertThat(blogPostResult.isPostExists()).isTrue();
    }

    private BlogPost blogPost() throws Exception {
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(getResource("com/nakedgardener/application/blog/blog-post_rest_response.json"), BlogPost.class);
    }
}