package com.nakedgardener.application.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

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
