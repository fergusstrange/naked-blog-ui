package com.nakedgardener.application.blog.recentblogposts;

import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.domain.BlogPosts.BlogPost;
import com.nakedgardener.application.blog.recentblogposts.dto.BlogPostPreview;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.nakedgardener.application.blog.recentblogposts.dto.BlogPostPreview.blogPostPreviewBuilder;
import static com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult.blogPostsResultsBuilder;

@Component
public class RecentBlogPostsDTOConverter implements Converter<BlogPosts, RecentBlogPostsResult> {

    @Override
    public RecentBlogPostsResult convert(BlogPosts blogPosts) {
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
