package com.nakedgardener.application.blog.dto;

import com.nakedgardener.application.blog.domain.BlogPosts.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class BlogPostsResult {

    private static final String noResultsMessage = "Like The Naked Gardener, the blog appears to be bare!";

    private final List<BlogPost> blogPosts;

    private BlogPostsResult(BlogPostsResultBuilder builder){
        this.blogPosts = builder.blogPosts;
    }

    public static BlogPostsResultBuilder blogPostsResultsBuilder() {
        return new BlogPostsResultBuilder();
    }

    public static BlogPostsResult emptyBlogPostResult() {
        return blogPostsResultsBuilder().build();
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public boolean isNoResults() {
        return blogPosts.isEmpty();
    }

    public String getNoResultsMessage() {
        return noResultsMessage;
    }

    public static class BlogPostsResultBuilder {

        private List<BlogPost> blogPosts = new ArrayList<>();

        public BlogPostsResultBuilder blogPosts(List<BlogPost> blogPosts) {
            this.blogPosts.addAll(blogPosts);
            return this;
        }

        public BlogPostsResult build() {
            return new BlogPostsResult(this);
        }
    }
}
