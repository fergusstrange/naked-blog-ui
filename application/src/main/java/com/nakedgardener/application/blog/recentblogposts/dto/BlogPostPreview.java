package com.nakedgardener.application.blog.recentblogposts.dto;

import java.util.Date;

public class BlogPostPreview {

    private final Date postDate;
    private final String title;
    private final String postSnippet;

    private BlogPostPreview(BlogPostPreviewBuilder builder) {
        this.postDate = builder.postDate;
        this.title = builder.title;
        this.postSnippet = builder.postSnippet;
    }

    public static BlogPostPreviewBuilder blogPostPreviewBuilder() {
        return new BlogPostPreviewBuilder();
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPostSnippet() {
        return postSnippet;
    }

    public static class BlogPostPreviewBuilder {

        private Date postDate;
        private String title;
        private String postSnippet;

        public BlogPostPreviewBuilder postDate(Date postDate) {
            this.postDate = postDate;
            return this;
        }

        public BlogPostPreviewBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BlogPostPreviewBuilder postSnippet(String postSnippet) {
            this.postSnippet = postSnippet;
            return this;
        }

        public BlogPostPreview build() {
            return new BlogPostPreview(this);
        }
    }
}
