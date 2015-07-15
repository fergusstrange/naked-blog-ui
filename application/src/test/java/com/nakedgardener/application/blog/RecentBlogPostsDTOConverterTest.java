package com.nakedgardener.application.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsDTOConverter;
import com.nakedgardener.application.blog.recentblogposts.dto.BlogPostPreview;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.junit.Test;

import static com.google.common.io.Resources.getResource;
import static org.fest.assertions.Assertions.assertThat;

public class RecentBlogPostsDTOConverterTest {

    private ObjectMapper objectMapper = new ObjectMapper();
    private RecentBlogPostsDTOConverter recentBlogPostsDTOConverter = new RecentBlogPostsDTOConverter();

    @Test
    public void shouldConvertBlogPostWithContent() throws Exception {
        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsDTOConverter.convert(blogPosts());

        assertThat(recentBlogPostsResult.isNoResults()).isFalse();
        assertThat(recentBlogPostsResult.getBlogPostPreviews()).hasSize(5);

        BlogPostPreview blogPostPreview = recentBlogPostsResult.getBlogPostPreviews().get(0);
        assertThat(blogPostPreview.getTitle()).isEqualTo("A Test Blog");
        assertThat(blogPostPreview.getPostSnippet()).isEqualTo("Some content in the blog!");
    }

    private BlogPosts blogPosts() throws Exception {
        return objectMapper.readValue(getResource("com/nakedgardener/application/blog/blog-posts_rest_response.json"), BlogPosts.class);
    }
}