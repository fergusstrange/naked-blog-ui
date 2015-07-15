package com.nakedgardener.application.blog.dto;

import java.util.ArrayList;
import java.util.List;

public class BlogPostsResult {

    private static final String noResultsMessage = "Like The Naked Gardener, the blog appears to be bare!";

    private final List<BlogPostPreview> blogPostPreviews;

    private BlogPostsResult(BlogPostsResultBuilder builder){
        this.blogPostPreviews = builder.blogPostPreviews;
    }

    public static BlogPostsResultBuilder blogPostsResultsBuilder() {
        return new BlogPostsResultBuilder();
    }

    public static BlogPostsResult emptyBlogPostResult() {
        return blogPostsResultsBuilder().build();
    }

    public List<BlogPostPreview> getBlogPostPreviews() {
        return blogPostPreviews;
    }

    public boolean isNoResults() {
        return blogPostPreviews.isEmpty();
    }

    public String getNoResultsMessage() {
        return noResultsMessage;
    }

    public static class BlogPostsResultBuilder {

        private List<BlogPostPreview> blogPostPreviews = new ArrayList<>();

        public BlogPostsResultBuilder blogPostPreviews(List<BlogPostPreview> blogPostPreviews) {
            this.blogPostPreviews.addAll(blogPostPreviews);
            return this;
        }

        public BlogPostsResult build() {
            return new BlogPostsResult(this);
        }
    }
}
