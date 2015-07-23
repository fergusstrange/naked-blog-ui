package com.nakedgardener.application.blog.recentblogposts.dto;

import java.time.LocalDateTime;

public class BlogPostPreview {

    private final LocalDateTime postDate;
    private final String title;
    private final String postSnippet;
    private final String blogPostSlugURL;

    private BlogPostPreview(BlogPostPreviewBuilder builder) {
        this.postDate = builder.postDate;
        this.title = builder.title;
        this.postSnippet = builder.postSnippet;
        this.blogPostSlugURL = builder.blogPostSlugURL;
    }

    public static BlogPostPreviewBuilder blogPostPreviewBuilder() {
        return new BlogPostPreviewBuilder();
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPostSnippet() {
        return postSnippet;
    }

    public String getBlogPostSlugURL() {
        return blogPostSlugURL;
    }

    public static class BlogPostPreviewBuilder {

        private LocalDateTime postDate;
        private String title;
        private String postSnippet;
        private String blogPostSlugURL;

        public BlogPostPreviewBuilder postDate(LocalDateTime postDate) {
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

        public BlogPostPreviewBuilder blogPostSlugURL(String blogPostSlugURL) {
            this.blogPostSlugURL = blogPostSlugURL;
            return this;
        }

        public BlogPostPreview build() {
            return new BlogPostPreview(this);
        }
    }
}
