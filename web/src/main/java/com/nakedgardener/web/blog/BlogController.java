package com.nakedgardener.web.blog;

import com.nakedgardener.application.blog.blogpost.BlogPostService;
import com.nakedgardener.application.blog.blogpost.dto.BlogPostResult;
import com.nakedgardener.application.blog.recentblogposts.RecentBlogPostsService;
import com.nakedgardener.application.blog.recentblogposts.dto.RecentBlogPostsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BlogController {

    private final RecentBlogPostsService recentBlogPostsService;
    private final BlogPostService blogPostService;

    @Autowired
    public BlogController(RecentBlogPostsService recentBlogPostsService, BlogPostService blogPostService) {
        this.recentBlogPostsService = recentBlogPostsService;
        this.blogPostService = blogPostService;
    }

    @RequestMapping(method = GET, value = "/blog")
    public String blog(final ModelMap model, @RequestParam(defaultValue = "0") Integer page) {
        RecentBlogPostsResult recentBlogPostsResult = recentBlogPostsService.blogPostsByIndex(page);
        model.addAttribute("recentBlogPostsResult", recentBlogPostsResult);
        return "blog";
    }

    @RequestMapping(method = GET, value = "/blog/{blogTitleSlug}")
    public String blogBySlug(final ModelMap model, @PathVariable String blogPostSlug) {
        BlogPostResult blogPostResult = blogPostService.blogPostByBlogPostSlug(blogPostSlug);
        model.addAttribute("blogPostResult", blogPostResult);
        return "blogPost";
    }
}
