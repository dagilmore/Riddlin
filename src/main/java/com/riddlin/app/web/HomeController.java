package com.riddlin.app.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riddlin.app.domain.account.UserAccount;
import com.riddlin.app.domain.riddle.Riddle;
import com.riddlin.app.message.Message;
import com.riddlin.app.message.MessageType;
import com.riddlin.app.web.AbstractPublicPageController;

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

        //Get recent blog posts, add to model
        List<Riddle> riddles = riddleService.getAllRiddles();
        if (riddles.size() > 0) {
            uiModel.addAttribute("riddles", riddles);
        } 

        //If no riddles, return a message
        else {
            uiModel.addAttribute("riddles", null);
            uiModel.addAttribute("riddleMessage", 
                    new Message(MessageType.INFO, "No riddles yet. Stay tuned..."));

        }

        //Render home.jsp with uiModel
        return "pages/home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model uiModel, HttpServletRequest request) {
        counterService.logVisit();
        return "pages/about";
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public String profile(@PathVariable("userId") String userId, Model uiModel, HttpServletRequest request) {
        counterService.logVisit();
        UserAccount account = this.accountService.findByUserId(userId);
        if (account == null){
            logger.warn("Access profle page with wrong userId : "+userId);
            return "resourceNotFound";
        }
        account.setConnections(accountService.getConnectionsByUserId(account.getUserId()));
        
        uiModel.addAttribute("profileUser", account);
        
        return "pages/userProfile";
    }

    @RequestMapping(value="/signin", method=RequestMethod.GET)
    public String signin(Model uiModel) {
        return "pages/signin";
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
