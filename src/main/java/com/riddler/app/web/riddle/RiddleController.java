package com.riddler.app.web.riddle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

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

import com.riddler.app.domain.riddle.Riddle;
import com.riddler.app.domain.riddle.RiddleService;
import com.riddler.app.domain.account.AccountUtils;
import com.riddler.app.domain.account.UserAccount;
import com.riddler.app.message.Message;
import com.riddler.app.message.MessageType;
import com.riddler.app.web.AbstractPublicPageController;


/**
 * Controller for public riddle list, riddle view pages.
 */
@Controller
public class RiddleController extends AbstractPublicPageController {
    private static final Logger logger = LoggerFactory.getLogger(RiddleController.class);

    @Inject
    private RiddleService riddleService;

    public RiddleController() {
    }

    /**
     * Display list of all riddles. 
     * 
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(value="/riddles", method = RequestMethod.GET)
    public String getRiddles(Model uiModel, HttpServletRequest request) {
        List<Riddle> riddles = riddleService.getAllRiddles();
        uiModel.addAttribute("riddles",riddles);
        return "riddles";
    }

    /**
     * Display a riddle by its id
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/riddle/{id}", method = RequestMethod.GET)
    public String getRiddleById( String riddleId, Model uiModel, HttpServletRequest request) {
        counterService.logVisit();
        Riddle riddle = riddleService.findByRiddleId(riddleId);
        uiModel.addAttribute("riddle", riddle);

        return "pages/riddle";
    }


}
