package com.nakedgardener.application.blog.blogpost.dto;

import java.time.LocalDateTime;

public class BlogPostResult {

    private static final String postNotExistsMessage = "Sorry, this post doesn't exist.";

    private final LocalDateTime postDate;
    private final String title;
    private final String post;
    private final boolean postExists;

    private BlogPostResult(BlogPostResultBuilder builder) {
        this.postDate = builder.postDate;
        this.title = builder.title;
        this.post = builder.post;
        this.postExists = builder.postExists;
    }

    public static BlogPostResultBuilder blogPostResultBuilder() {
        return new BlogPostResultBuilder().withDefaults();
    }

    public static BlogPostResult emptyBlogPostResult() {
        return blogPostResultBuilder().postExists(false).build();
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public boolean isPostExists() {
        return postExists;
    }

    public String getPostNotExistsMessage() {
        return postNotExistsMessage;
    }

    public static class BlogPostResultBuilder {

        private LocalDateTime postDate;
        private String title;
        private String post;
        private boolean postExists;

        private BlogPostResultBuilder withDefaults() {
            return postExists(true);
        }

        public BlogPostResultBuilder postDate(LocalDateTime postDate) {
            this.postDate = postDate;
            return this;
        }

        public BlogPostResultBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BlogPostResultBuilder post(String post) {
            this.post = post;
            return this;
        }

        public BlogPostResultBuilder postExists(boolean postExists) {
            this.postExists = postExists;
            return this;
        }

        public BlogPostResult build() {
            return new BlogPostResult(this);
        }
    }
}
