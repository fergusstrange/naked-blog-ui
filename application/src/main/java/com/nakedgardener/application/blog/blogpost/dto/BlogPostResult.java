package com.nakedgardener.application.blog.blogpost.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BlogPostResult {

    private static final String postNotExistsMessage = "Sorry, this post doesn't exist.";

    private final String postDate;
    private final String title;
    private final String post;
    private final boolean postExists;

    public String getPostNotExistsMessage() {
        return postNotExistsMessage;
    }

    public static BlogPostResult emptyBlogPostResult() {
        return builder().postExists(false).build();
    }
}
