package com.nakedgardener.application.blog;

import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.domain.BlogPosts.BlogPost;
import com.nakedgardener.application.blog.dto.BlogPostPreview;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.nakedgardener.application.blog.dto.BlogPostPreview.blogPostPreviewBuilder;
import static com.nakedgardener.application.blog.dto.BlogPostsResult.blogPostsResultsBuilder;

@Component
public class BlogPostDTOConverter implements Converter<BlogPosts, BlogPostsResult> {

    @Override
    public BlogPostsResult convert(BlogPosts blogPosts) {
        return blogPostsResultsBuilder()
                .blogPostPreviews(blogPostPreviews(blogPosts.getBlogPosts()))
                .build();
    }

    private List<BlogPostPreview> blogPostPreviews(List<BlogPost> blogPosts) {
        return blogPosts.stream().map(blogPost ->
                blogPostPreviewBuilder()
                .postDate(blogPost.getPostDate())
                .title(blogPost.getTitle())
                .postSnippet(blogPost.getPost())
                .build()
        ).collect(Collectors.toList());
    }
}
