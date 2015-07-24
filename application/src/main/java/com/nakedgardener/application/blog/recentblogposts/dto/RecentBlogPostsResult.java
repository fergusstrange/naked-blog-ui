package com.nakedgardener.application.blog.recentblogposts.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import static java.util.Collections.emptyList;

@Data
@Builder
public class RecentBlogPostsResult {

    private static final String noResultsMessage = "Like The Naked Gardener, the blog appears to be bare!";

    private final List<BlogPostPreview> blogPostPreviews;

    public boolean isNoResults() {
        return blogPostPreviews.isEmpty();
    }

    public String getNoResultsMessage() {
        return noResultsMessage;
    }

    public static RecentBlogPostsResult emptyBlogPostResult() {
        return builder().blogPostPreviews(emptyList()).build();
    }
}
