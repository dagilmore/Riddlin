package com.riddlin.app.controller.web.riddle;

import javax.servlet.http.HttpServletRequest;

import com.riddlin.app.controller.web.AbstractPublicPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* Controller for crossword actions, riddle view page.
*/
@Controller
@RequestMapping("/crossword")
public class CrosswordController extends AbstractPublicPageController {
    private static final Logger logger = LoggerFactory.getLogger(CrosswordController.class);

    public CrosswordController() {}
    
    @RequestMapping( method = RequestMethod.GET)
    public String crossword( Model uiModel, HttpServletRequest request ) {


        //Implement a model with a crossword puzzle

        return "pages/riddle";
    }

}