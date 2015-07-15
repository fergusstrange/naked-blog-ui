package com.nakedgardener.application.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.dto.BlogPostPreview;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
import org.junit.Test;

import static com.google.common.io.Resources.getResource;
import static org.fest.assertions.Assertions.assertThat;

public class BlogPostDTOConverterTest {

    private ObjectMapper objectMapper = new ObjectMapper();
    private BlogPostDTOConverter blogPostDTOConverter = new BlogPostDTOConverter();

    @Test
    public void shouldConvertBlogPostWithContent() throws Exception {
        BlogPostsResult blogPostsResult = blogPostDTOConverter.convert(blogPosts());

        assertThat(blogPostsResult.isNoResults()).isFalse();
        assertThat(blogPostsResult.getBlogPostPreviews()).hasSize(5);

        BlogPostPreview blogPostPreview = blogPostsResult.getBlogPostPreviews().get(0);
        assertThat(blogPostPreview.getTitle()).isEqualTo("A Test Blog");
        assertThat(blogPostPreview.getPostSnippet()).isEqualTo("Some content in the blog!");
    }

    private BlogPosts blogPosts() throws Exception {
        return objectMapper.readValue(getResource("com/nakedgardener/application/blog/blog-posts_rest_response.json"), BlogPosts.class);
    }
}