package com.nakedgardener.application.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPosts {

    @JsonProperty
    private List<BlogPost> blogPosts;
}
