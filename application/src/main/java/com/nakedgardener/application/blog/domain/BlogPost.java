package com.nakedgardener.application.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
}
