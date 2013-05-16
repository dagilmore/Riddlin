package com.riddlin.app.controller.web.riddle;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import com.riddlin.app.controller.web.AbstractPublicPageController;
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
public class RiddleController extends AbstractPublicPageController {
    private static final Logger logger = LoggerFactory.getLogger(RiddleController.class);

    public RiddleController() {}
    
    @RequestMapping( value = "/riddles" ,method = RequestMethod.GET )
    public String riddles( Model uiModel, HttpServletRequest request ) {
    
    	String[] links = { 
    		"wordGames" , 
    		"puzzles" ,
    		"conundrums" 
    	};
    	uiModel.addAttribute("links", links);

        return "pages/riddles";
    }

    @RequestMapping( value = "/wordGames" , method = RequestMethod.GET )
    public String wordGames( Model uiModel, HttpServletRequest request ) {
    
    	String[] links = { 
    		"americanIdioms"
    	};
    	uiModel.addAttribute("links", links);

        return "pages/riddles";
    }

    @RequestMapping( value = "/puzzles" , method = RequestMethod.GET )
    public String puzzles( Model uiModel, HttpServletRequest request ) {
    
    	String[] links = { 
    		"puzzles/sudoku" , 
    		"puzzles/crossword"
    	};
    	uiModel.addAttribute("links", links);

        return "pages/riddles";
    }

    @RequestMapping( value = "/conundrums" , method = RequestMethod.GET )
    public String conundrums( Model uiModel, HttpServletRequest request ) {
    
    	String[] links = { 
    	};
    	uiModel.addAttribute("links", links);

        return "pages/riddles";
    }

}