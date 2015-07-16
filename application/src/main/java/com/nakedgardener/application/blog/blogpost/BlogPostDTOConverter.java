package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.nakedgardener.application.blog.blogpost.dto.BlogPostResult.blogPostResultBuilder;

@Component
public class BlogPostDTOConverter implements Converter<BlogPost, BlogPostResult> {

    @Override
    public BlogPostResult convert(BlogPost blogPost) {
        return blogPostResultBuilder()
                .postDate(blogPost.getPostDate())
                .title(blogPost.getTitle())
                .post(blogPost.getPost())
                .build();
    }
}
