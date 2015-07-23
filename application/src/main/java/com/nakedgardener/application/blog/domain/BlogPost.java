package com.nakedgardener.application.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

    @JsonProperty
    private String id;

    @JsonProperty
    private LocalDateTime postDate;

    @JsonProperty
    private String title;

    @JsonProperty
    private String post;

    @JsonProperty
    private String blogPostSlug;

    public String getId() {
        return id;
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

    public String getBlogPostSlug() {
        return blogPostSlug;
    }
}
