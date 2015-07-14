package com.nakedgardener.web;

import com.nakedgardener.application.blog.BlogService;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(method = GET, value = "/blog")
    public String blog(final ModelMap model, @RequestParam("1") Integer page) {
        BlogPostsResult blogPostsResult = blogService.blogPostsByIndex(0);
        model.addAttribute("blogPostsResult", blogPostsResult);
        return "blog";
    }
}
