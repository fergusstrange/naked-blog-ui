package com.nakedgardener.application.blog.recentblogposts.dto;

import java.util.ArrayList;
import java.util.List;

public class RecentBlogPostsResult {

    private static final String noResultsMessage = "Like The Naked Gardener, the blog appears to be bare!";

    private final List<BlogPostPreview> blogPostPreviews;

    private RecentBlogPostsResult(BlogPostsResultBuilder builder){
        this.blogPostPreviews = builder.blogPostPreviews;
    }

    public static BlogPostsResultBuilder blogPostsResultsBuilder() {
        return new BlogPostsResultBuilder();
    }

    public static RecentBlogPostsResult emptyBlogPostResult() {
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

        public RecentBlogPostsResult build() {
            return new RecentBlogPostsResult(this);
        }
    }
}
