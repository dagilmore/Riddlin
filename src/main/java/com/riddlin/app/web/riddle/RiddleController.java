package com.riddlin.app.web.riddle;

import javax.servlet.http.HttpServletRequest;

import com.riddlin.app.web.AbstractPublicPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* Controller for public riddle list, riddle view pages.
*/
@Controller
@RequestMapping("/riddles")
public class RiddleController extends AbstractPublicPageController {
    private static final Logger logger = LoggerFactory.getLogger(RiddleController.class);

    public RiddleController() {}

    @RequestMapping( method = RequestMethod.GET)
    public String getAmericanIdioms(Model uiModel, HttpServletRequest request) {
    	
        return "pages/riddles";
    }
}