package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.fest.assertions.Assertions.assertThat;

public class BlogPostDTOConverterTest {

    private BlogPostDTOConverter blogPostDTOConverter = new BlogPostDTOConverter();

    @Test
    public void shouldConvertBlogPostToBlogPostResult() throws Exception {
        BlogPostResult blogPostResult = blogPostDTOConverter.convert(
                BlogPost.builder()
                .title("A Test Blog")
                .post("Some content in the blog!")
                .postDate(LocalDateTime.of(2014, 2, 2, 15, 9))
                .build()
        );

        assertThat(blogPostResult).isNotNull();
        assertThat(blogPostResult.getTitle()).isEqualTo("A Test Blog");
        assertThat(blogPostResult.getPost()).isEqualTo("Some content in the blog!");
        assertThat(blogPostResult.getPostDate()).isEqualTo("02-02-2014 15:09");
        assertThat(blogPostResult.isPostExists()).isTrue();
    }

    @Test
    public void shouldConvertNullDateToNull() throws Exception {
        BlogPostResult blogPostResult = blogPostDTOConverter.convert(BlogPost.builder()
                .postDate(null)
                .post("rubbish text.")
                .blogPostSlug("dad-dasd-das-d23d3")
                .build());

        assertThat(blogPostResult.getPostDate()).isNull();
    }
}