package com.riddlin.app.web;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.riddlin.app.domain.account.AccountService;
import com.riddlin.app.domain.account.AccountUtils;
import com.riddlin.app.domain.account.UserAccount;
import com.riddlin.app.domain.system.CounterService;
import com.riddlin.app.domain.riddle.RiddleService;

public class AbstractPageController {
    @Inject
    protected AccountService accountService;
    @Inject
    protected CounterService counterService;
    @Inject
    protected RiddleService riddleService;


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
