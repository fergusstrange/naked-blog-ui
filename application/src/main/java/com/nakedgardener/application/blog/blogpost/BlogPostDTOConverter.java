package com.nakedgardener.application.blog.blogpost;

import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.domain.BlogPost;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nakedgardener.application.blog.blogpost.dto.BlogPostResult.blogPostResultBuilder;
import static java.time.format.FormatStyle.SHORT;

@Component
public class BlogPostDTOConverter implements Converter<BlogPost, BlogPostResult> {

    @Override
    public BlogPostResult convert(BlogPost blogPost) {
        return blogPostResultBuilder()
                .postDate(formattedPostDate(blogPost.getPostDate()))
                .title(blogPost.getTitle())
                .post(blogPost.getPost())
                .build();
    }

    private String formattedPostDate(LocalDateTime postDate) {
        return postDate.format(DateTimeFormatter.ofLocalizedDateTime(SHORT));
    }
}
