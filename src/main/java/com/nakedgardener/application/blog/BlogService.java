package com.nakedgardener.application.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlogService {

    private final RestTemplate restTemplate;

    @Autowired
    public BlogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
