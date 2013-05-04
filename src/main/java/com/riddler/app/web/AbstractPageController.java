package com.riddler.app.web;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.riddler.app.domain.account.AccountService;
import com.riddler.app.domain.account.AccountUtils;
import com.riddler.app.domain.account.UserAccount;
import com.riddler.app.domain.post.BlogPostService;
import com.riddler.app.domain.post.CommentPostService;
import com.riddler.app.domain.post.SlidePostService;
import com.riddler.app.domain.system.CounterService;

public class AbstractPageController {
    @Inject
    protected AccountService accountService;
    @Inject
    protected BlogPostService blogPostService;
    @Inject
    protected CommentPostService commentPostService;
    @Inject
    protected SlidePostService slidePostService;
    @Inject
    protected CounterService counterService;

    @ModelAttribute("loggedinUserAccount")
    public UserAccount addLoggedinUserAccount() {
        return AccountUtils.getLoginUserAccount();
    }
    
    protected String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    String getUserIpAddress(HttpServletRequest request) {
        return request.getHeader("x-forwarded-for");
    }

}
