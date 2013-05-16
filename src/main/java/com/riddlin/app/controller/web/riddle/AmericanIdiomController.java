package com.riddlin.app.controller.web.riddle;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Random;

import com.riddlin.app.controller.web.AbstractPublicPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.context.annotation.Scope;
import com.riddlin.app.domain.riddle.americanIdiom.AmericanIdiom;
import com.riddlin.app.message.Message;
import com.riddlin.app.message.MessageType;


/**
* Controller for american idiom interactions, riddle view pages.
*/
@Controller
@Scope("session")
@RequestMapping("/americanIdioms")
public class AmericanIdiomController extends AbstractPublicPageController {
    
    private static final Logger logger = LoggerFactory.getLogger(AmericanIdiomController.class);

    AmericanIdiom americanIdiom;

    public AmericanIdiomController() {}

    /**
    * Get a random american idiom
    *
    * @param uiModel
    * @param request
    * @return
    */
    @RequestMapping(method = RequestMethod.GET)
    public String getNextAmericanIdiom(Model uiModel, HttpServletRequest request) {

        Random r = new Random();
        Long index = new Long((long)(r.nextInt(2200)+1));

        // Get a random american idioms and add it to the model
        americanIdiom = americanIdiomService.findAmericanIdiomById(index);
        if (americanIdiom != null) {
            uiModel.addAttribute("riddle", americanIdiom.getInitialState());
            uiModel.addAttribute("type", "americanIdiom");
            uiModel.addAttribute("path", "/WEB-INF/views/partials/riddles/americanIdiom.jsp");
        }

        //If no americanIdioms, return a message
        else {
            uiModel.addAttribute("riddle", null);
            uiModel.addAttribute("riddleMessage",
                    new Message(MessageType.INFO, "No American Idioms yet. Stay tuned..."));

        }
        return "pages/riddle";
    }

    @RequestMapping(value = "/{americanIdiomId}", method = RequestMethod.GET)
    public String getAmericanIdiomById(@PathVariable("americanIdiomId") Long americanIdiomId, Model uiModel) {

        //Get an americanIdiom by Id. 
        americanIdiom = americanIdiomService.findAmericanIdiomById(americanIdiomId);
        return americanIdiom.getInitialState();
    }

    @RequestMapping( method = RequestMethod.POST )
    public String getNextClue( Model uiModel, HttpServletRequest request, 
        @RequestParam(value="character", required=false) char character) { 

        if(character > -1) {
            uiModel.addAttribute("riddle", americanIdiom.getClue(character));
            uiModel.addAttribute("type", "americanIdiom");
            uiModel.addAttribute("path", "/WEB-INF/views/partials/riddles/americanIdiom.jsp");
        }

        else {
            uiModel.addAttribute("riddle",  americanIdiom.getClue('*'));
            uiModel.addAttribute("type", "americanIdiom");
        }

        return "pages/riddle";
    }

}