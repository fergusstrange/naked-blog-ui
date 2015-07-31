package com.nakedgardener.application.blog.recentblogposts;

import com.nakedgardener.application.blog.domain.BlogPost;
import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.recentblogposts.dto.BlogPostPreview;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.nakedgardener.application.blog.recentblogposts.dto.BlogPostPreview.blogPostPreviewBuilder;
import static com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult.emptyBlogPostResult;
import static java.util.Objects.nonNull;

@Component
public class RecentBlogPostsDTOConverter implements Converter<BlogPosts, RecentBlogPostsResult> {

    private final BlogPostUrlFactory blogPostUrlFactory;

    @Autowired
    public RecentBlogPostsDTOConverter(BlogPostUrlFactory blogPostUrlFactory) {
        this.blogPostUrlFactory = blogPostUrlFactory;
    }

    @Override
    public RecentBlogPostsResult convert(BlogPosts blogPosts) {
        return nonNull(blogPosts) ?
                recentBlogPostsResult(blogPosts) :
                emptyBlogPostResult();
    }

    private RecentBlogPostsResult recentBlogPostsResult(BlogPosts blogPosts) {
        return RecentBlogPostsResult.builder()
                .blogPostPreviews(blogPostPreviews(blogPosts.getBlogPosts()))
                .build();
    }

    private List<BlogPostPreview> blogPostPreviews(List<BlogPost> blogPosts) {
        return blogPosts.stream().map(blogPost ->
                blogPostPreviewBuilder()
                .postDate(blogPost.getPostDate())
                .title(blogPost.getTitle())
                .postSnippet(blogPost.getPost())
                .blogPostSlugURL(blogPostUrlFactory.blogPostURL(blogPost.getBlogPostSlug()))
                .build()
        ).collect(Collectors.toList());
    }
}
