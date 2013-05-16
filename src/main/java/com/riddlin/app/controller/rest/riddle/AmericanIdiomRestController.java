package com.riddlin.app.controller.rest.riddle;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import com.riddlin.app.controller.web.AbstractPublicPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.annotation.Scope;
import com.riddlin.app.domain.riddle.americanIdiom.AmericanIdiom;
import com.riddlin.app.message.Message;
import com.riddlin.app.message.MessageType;


/**
* Controller for american idiom rest services
*/
@Controller
@RequestMapping("/rest/americanIdioms")
public class AmericanIdiomRestController extends AbstractPublicPageController {
    
    private static final Logger logger = LoggerFactory.getLogger(AmericanIdiomRestController.class);

    public AmericanIdiomRestController() {}

   /**
   * Display list of all riddles.
   *
   * @param request
   * @return americanIdioms  Json array of American Idioms
   */
   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   public List<AmericanIdiom> getAmericanIdioms(HttpServletRequest request) {

       //Get americanIdioms, add to model
       List<AmericanIdiom> americanIdioms = americanIdiomService.getAllAmericanIdioms();
       return americanIdioms;

   }

   /**
   * Get an american idiom by id
   *
   * @param americanIdiomId   Path variable
   * @param uiModel
   * @return americanIdiom    Json array of American Idioms
   */
   @RequestMapping(value = "/{americanIdiomId}", method = RequestMethod.GET)
   @ResponseBody
   public AmericanIdiom getAmericanIdiomById(@PathVariable("americanIdiomId") Long americanIdiomId, Model uiModel) {

       //Get an americanIdiom by Id.
       AmericanIdiom americanIdiom = americanIdiomService.findAmericanIdiomById(americanIdiomId);
       return americanIdiom;
   }
}