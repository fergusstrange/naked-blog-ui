package com.nakedgardener.application.blog.blogpost.dto;

import java.util.Date;

public class BlogPostResult {

    private final Date postDate;
    private final String title;
    private final String post;

    private BlogPostResult(BlogPostResultBuilder builder) {
        this.postDate = builder.postDate;
        this.title = builder.title;
        this.post = builder.post;
    }

    public static BlogPostResultBuilder blogPostResultBuilder() {
        return new BlogPostResultBuilder();
    }

    public static BlogPostResult emptyBlogPostResult() {
        return blogPostResultBuilder().build();
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public static class BlogPostResultBuilder {

        private Date postDate;
        private String title;
        private String post;

        public BlogPostResultBuilder postDate(Date postDate) {
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

        public BlogPostResult build() {
            return new BlogPostResult(this);
        }
    }
}
