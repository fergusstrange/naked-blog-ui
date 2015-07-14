package com.nakedgardener.application.blog.dto;

import com.nakedgardener.application.blog.domain.BlogPosts.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class BlogPostsResult {

    private final List<BlogPost> blogPosts;
    private final boolean noResults;

    private BlogPostsResult(BlogPostsResultBuilder builder){
        this.blogPosts = builder.blogPosts;
        this.noResults = builder.noResults;
    }

    public static BlogPostsResultBuilder blogPostsResultsBuilder() {
        return new BlogPostsResultBuilder();
    }

    public static BlogPostsResult emptyBlogPostResult() {
        return blogPostsResultsBuilder().noResults().build();
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public boolean isNoResults() {
        return noResults;
    }

    public static class BlogPostsResultBuilder {

        private List<BlogPost> blogPosts = new ArrayList<>();
        private boolean noResults;

        public BlogPostsResultBuilder blogPosts(List<BlogPost> blogPosts) {
            this.blogPosts.addAll(blogPosts);
            return this;
        }

        public BlogPostsResultBuilder noResults() {
            this.noResults = true;
            return this;
        }

        public BlogPostsResult build() {
            return new BlogPostsResult(this);
        }
    }
}
