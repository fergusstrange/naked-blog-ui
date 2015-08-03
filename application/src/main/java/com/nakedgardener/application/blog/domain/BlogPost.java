package com.nakedgardener.application.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
