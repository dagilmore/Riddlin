package com.riddlin.app.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riddlin.app.domain.account.UserAccount;

@Controller
public class HomeController extends AbstractPublicPageController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController() {
    }

    /**
     * Checks for news and riddles
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model uiModel, HttpServletRequest request) {

        counterService.logVisit();

        //Render home.jsp with uiModel
        return "pages/home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model uiModel, HttpServletRequest request) {
        counterService.logVisit();
        return "pages/about";
    }
    
    /**
     * Handle exceptions.
     */
    @RequestMapping(value = "/uncaughtException", method = RequestMethod.GET)
    public String uncaughtException(Model uiModel) {
        return "pages/uncaughtException";
    }

    @RequestMapping(value = "/resourceNotFound", method = RequestMethod.GET)
    public String resourceNotFound(Model uiModel) {
        return "pages/resourceNotFound";
    }

}
