package com.riddlin.app.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.riddlin.app.domain.post.BlogPost;
import com.riddlin.app.domain.post.CommentPost;


public class AbstractPublicPageController extends AbstractPageController{
    public static final int RECENT_POST_COUNT = 4;
    
    public AbstractPublicPageController(){
    }
    
    @ModelAttribute("recentPosts")
    public List<BlogPost> addRecentPosts() {
        return blogPostService.getPublishedPosts(RECENT_POST_COUNT);
    }
    
    @ModelAttribute("recentComments")
    public List<CommentPost> addRecentComments() {
        return commentPostService.getPublishedComments(RECENT_POST_COUNT);
    }

    @ModelAttribute("tagCloud")
    public Map<String, Integer> addTagCloud() {
        return blogPostService.getTagCloud();
    }

    @ModelAttribute("pageVisit")
    public long addPageVisit() {
        return counterService.getVisitCount();
    }

}
