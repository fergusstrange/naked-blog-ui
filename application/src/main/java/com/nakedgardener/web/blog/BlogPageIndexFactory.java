package com.nakedgardener.web.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogPageIndexFactory {

    private final int chunkSize;

    @Autowired
    public BlogPageIndexFactory(@Value("${blog.chunk.size:5}") int chunkSize) {
        this.chunkSize = chunkSize;
    }

    int indexFromPage(int page) {
        return (page - 1) * chunkSize;
    }
}