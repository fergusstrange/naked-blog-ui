package com.nakedgardener.application.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPosts {

    @JsonProperty
    private List<BlogPost> blogPosts;

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BlogPost {

        @JsonProperty
        private String id;

        @JsonProperty
        private Date postDate;

        @JsonProperty
        private String title;

        @JsonProperty
        private String post;

        public String getId() {
            return id;
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
    }
}
