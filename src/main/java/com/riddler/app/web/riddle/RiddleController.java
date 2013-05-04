package com.riddler.app.web.riddle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riddler.app.domain.account.AccountUtils;
import com.riddler.app.domain.account.UserAccount;
import com.riddler.app.domain.post.BlogPost;
import com.riddler.app.domain.post.CommentPost;
import com.riddler.app.domain.post.SlidePost;
import com.riddler.app.message.Message;
import com.riddler.app.message.MessageType;
import com.riddler.app.web.AbstractPublicPageController;
import com.riddler.app.web.CommentNotificationSender;
import com.riddler.app.web.PageWrapper;

/**
 * Controller for public riddle list, riddle view pages.
 */
@Controller
public class RiddleController extends AbstractPublicPageController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    
    public BlogController() {
    }

    /**
     * Display list of all riddles. 
     * 
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(value={"/", "/riddles"}, method = RequestMethod.GET)
    public String blog(Model uiModel, HttpServletRequest request) {

        counterService.logVisit();
        uiModel.addAttribute("page", page);
        return "site/blogs";
    }

    /**
     * Display list of all public blogs with specified tag.
     * @param tag
     * @param uiModel
     * @param request
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/blog/tag/{tag}", method = RequestMethod.GET)
    public String blogWithTag(@PathVariable("tag") String tag, Model uiModel, HttpServletRequest request, Pageable pageable) {
        counterService.logVisit();
        PageWrapper<BlogPost> page = new PageWrapper<BlogPost>(blogPostService.getPublishedPostsByTag(tag, pageable), "/blog/tag/"+tag);
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("message", 
                new Message(MessageType.INFO, "Blog posts with tag <span class=\"label label-success\">"+tag+"</span>:"));

        return "site/blogs";
    }

    /**
     * For single blog post page.
     * 
     * @param year
     * @param month
     * @param path
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/post/{year}/{month}/{path}", produces = "text/html")
    public String displayPost(@PathVariable("year") int year, @PathVariable("month") int month,
            @PathVariable("path") String path, Model uiModel, HttpServletRequest request) {
        counterService.logVisit();
        BlogPost blogPost = blogPostService.getPostByPublishedPath(year, month, path);

        if (blogPost == null) {
            return "redirect:/resourceNotFound";
        }
        counterService.logBlogPostVisit(blogPost.getId());
        // this.adminService.recordAccess(getUserIpAddress(request), blogPost.getFullPublishedPath());
        uiModel.addAttribute("blog", blogPost);
        uiModel.addAttribute("commentForm", new CommentForm());
        return "site/post";
    }

    /**
     * Post comment from blog post page.
     * 
     * @param id
     * @param commentForm
     * @param bindingResult
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/comment/{blogPostId}", method = RequestMethod.POST, produces = "text/html")
    public String postComment(@PathVariable("blogPostId") String blogPostId,
            @ModelAttribute("commentForm") @Valid CommentForm commentForm, BindingResult bindingResult, Model uiModel,
            HttpServletRequest httpServletRequest) {
        logger.debug("==>BlogController.postComment() for blogPostId =" + blogPostId);
        
        //get login user id
        UserAccount account = AccountUtils.getLoginUserAccount();
        if (account == null){
            logger.warn("Cannot post comment without login");
            return "redirect:/signin";
        }
        
        CommentPost comment = commentPostService.addComment(blogPostId, commentForm.getContent());
        if (comment == null){
            return "redirect:/blog";
        }

        BlogPost blogPost = this.blogPostService.getPublishedPostById(blogPostId);
        
        if (this.commentNotificationSender != null){
            //send notification email to bog post author
            comment.setBlogPost(blogPost);
            this.commentNotificationSender.send(comment);
        }
        
        if (!comment.isPublished()){
            uiModel.addAttribute("blog", blogPost);
            uiModel.addAttribute("commentForm", new CommentForm());
            uiModel.addAttribute("message", 
                     new Message(MessageType.INFO, "Thank you for the feedback. Your comment will be published after approval."));
            return "site/post";
        }

        return "redirect:/post/" + blogPost.getFullPublishedPath();
    }
    
    /**
     * For single slide page.
     * 
     * @param year
     * @param month
     * @param path
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/presentation/{path}", produces = "text/html")
    public String displaySlide(@PathVariable("path") String path, Model uiModel, HttpServletRequest request) {
        SlidePost slidePost = slidePostService.getSlideByPublishedPath(path);

        if (slidePost == null) {
            return "redirect:/resourceNotFound";
        }
        counterService.logSlidePostVisit(slidePost.getId());
        // this.adminService.recordAccess(getUserIpAddress(request), blogPost.getFullPublishedPath());
        uiModel.addAttribute("slide", slidePost);
        //TODO return html template based on type.
        return "deckjsPresentation";
    }

}
