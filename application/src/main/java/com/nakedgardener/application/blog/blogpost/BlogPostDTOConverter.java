package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPosts;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogPostDTOConverter implements Converter<BlogPosts, BlogPostResult> {

    @Override
    public BlogPostResult convert(BlogPosts source) {
        return new BlogPostResult();
    }
}
