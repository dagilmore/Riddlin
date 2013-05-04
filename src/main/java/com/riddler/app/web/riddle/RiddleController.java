package com.riddler.app.web.riddle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riddler.app.domain.riddle.Riddle;
import com.riddler.app.domain.riddle.RiddleService;
import com.riddler.app.message.Message;
import com.riddler.app.message.MessageType;
import com.riddler.app.web.AbstractPublicPageController;


/**
 * Controller for public riddle list, riddle view pages.
 */
@Controller
@RequestMapping("/riddles")
public class RiddleController extends AbstractPublicPageController {
    // private static final Logger logger = LoggerFactory.getLogger(RiddleController.class);

    public RiddleController() {
    }

    /**
     * Display list of all riddles. 
     * 
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getRiddles(Model uiModel, HttpServletRequest request) {

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
        return "pages/riddles";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public @ResponseBody Riddle create(
            @RequestParam(value="riddle", required=true) String riddle,
            @RequestParam(value="answer", required=true) String answer){

        Riddle newRiddle = new Riddle();
        newRiddle.setRiddle(riddle);
        newRiddle.setAnswer(answer);

        return riddleService.create(newRiddle);
    }


}








