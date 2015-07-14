package com.nakedgardener.application.blog;

import com.nakedgardener.application.blog.domain.BlogPosts;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.nakedgardener.application.blog.dto.BlogPostsResult.blogPostsResultsBuilder;

@Component
public class BlogPostDTOConverter implements Converter<BlogPosts, BlogPostsResult> {

    @Override
    public BlogPostsResult convert(BlogPosts blogPosts) {
        return blogPostsResultsBuilder()
                .blogPosts(blogPosts.getBlogPosts())
                .build();
    }
}
