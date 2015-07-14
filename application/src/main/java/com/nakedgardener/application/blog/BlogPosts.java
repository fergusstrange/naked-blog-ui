package com.nakedgardener.application.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPosts {

    @JsonProperty
    private List<BlogPost> blogPosts;

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }
}
