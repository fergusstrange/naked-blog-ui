package com.nakedgardener.web;

import com.nakedgardener.application.blog.RecentBlogService;
import com.nakedgardener.application.blog.dto.BlogPostsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BlogController {

    private final RecentBlogService recentBlogService;

    @Autowired
    public BlogController(RecentBlogService recentBlogService) {
        this.recentBlogService = recentBlogService;
    }

    @RequestMapping(method = GET, value = "/blog")
    public String blog(final ModelMap model, @RequestParam(defaultValue = "1") Integer page) {
        BlogPostsResult blogPostsResult = recentBlogService.blogPostsByIndex(0);
        model.addAttribute("blogPostsResult", blogPostsResult);
        return "blog";
    }
}
