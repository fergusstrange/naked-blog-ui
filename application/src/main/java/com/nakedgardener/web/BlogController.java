package com.nakedgardener.web;

import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BlogController {

    private final RecentBlogPostsService recentBlogPostsService;

    @Autowired
    public BlogController(RecentBlogPostsService recentBlogPostsService) {
        this.recentBlogPostsService = recentBlogPostsService;
    }

    @RequestMapping(method = GET, value = "/blog")
    public String blog(final ModelMap model, @RequestParam(defaultValue = "1") Integer page) {
        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(0);
        model.addAttribute("recentBlogPostsResult", recentBlogPostsResult);
        return "blog";
    }
}
