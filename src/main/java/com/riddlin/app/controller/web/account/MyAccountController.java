package com.riddlin.app.controller.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.riddlin.app.controller.web.AbstractPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.riddlin.app.domain.account.AccountUtils;
import com.riddlin.app.domain.account.UserAccount;


/**
 * Controller for account pages.
 */
@Controller
@RequestMapping("/myAccount")
public class MyAccountController extends AbstractPageController {
    private static final Logger logger = LoggerFactory.getLogger(MyAccountController.class);

    public MyAccountController() {
    }

    /**
     * Overview page, display login user account profile info and social connections.
     * 
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping(produces = "text/html")
    public String overview(Model uiModel, WebRequest request) {
        UserAccount account = AccountUtils.getLoginUserAccount();
        //reload user
        account = accountService.findByUserId(account.getUserId());
        account.setConnections(accountService.getConnectionsByUserId(account.getUserId()));
        uiModel.addAttribute("userAccount", account);
        
        //check duplicate connection error
        if (request.getParameter("social.addConnection.duplicate") != null){
            uiModel.addAttribute("duplicateConnectionError", Boolean.TRUE);
        }
        
        return "redirect:";
    }

    @RequestMapping(value = "/editProfile", produces = "text/html")
    public String editProfileForm(Model uiModel) {
        logger.debug("==>MyAccountController.editProfileForm()");
        UserAccount account = AccountUtils.getLoginUserAccount();
        //reload user
        account = accountService.findByUserId(account.getUserId());
        uiModel.addAttribute("userAccount", account);
        uiModel.addAttribute("profileForm", new ProfileForm(account));
        return "pages/myAccount/editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.PUT, produces = "text/html")
    public String editProfile(@ModelAttribute("profileForm") @Valid ProfileForm profileForm,
                    BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("userAccount", AccountUtils.getLoginUserAccount());
            uiModel.addAttribute("profileForm", profileForm);
            return "pages/myAccount/editProfile";
        }
        
        UserAccount account = AccountUtils.getLoginUserAccount();
        if (!account.getUserId().equals(profileForm.getUserId())){
            logger.warn("Update other user's profile by logged in user "+account.getUserId());
            return "redirect:/myAccount";
        }
        uiModel.asMap().clear();

        try {
            if (account.isAdmin()){
                account = accountService.updateProfile(profileForm.getUserId(), profileForm.getDisplayName(),
                            profileForm.getEmail(), profileForm.getWebSite(), profileForm.getImageUrl());
            } else {
                account = accountService.updateProfile(profileForm.getUserId(), profileForm.getDisplayName(),
                        profileForm.getEmail(), profileForm.getWebSite());
            }
            AccountUtils.getLoginUserAccount().setDisplayName(profileForm.getDisplayName()); //update display name. TODO refresh logged in user?
        } catch (UsernameNotFoundException ex) {
            logger.error("Something wrong, update profile with invalid account " + profileForm.getUserId());
            // TODO ? re-authenticate the user ?
            return "redirect:/";
        }
        return "redirect:/myAccount";
    }
    
    @RequestMapping(value = "/useGoogleImage", method = RequestMethod.GET, produces = "text/html")
    public String useGoogleImage(Model uiModel) {
        UserAccount account = AccountUtils.getLoginUserAccount();
        logger.debug("==>MyAccountController.useGoogleImage(), userId=" + account.getUserId());
        account.setConnections(accountService.getConnectionsByUserId(account.getUserId()));
        if (account.isHasGoogleConnection()){
            accountService.updateImageUrl(account.getUserId(), account.getGoogleConnection().getImageUrl());
        }
        return "redirect:/myAccount";
    }

    @RequestMapping(value = "/useTwitterImage", method = RequestMethod.GET, produces = "text/html")
    public String useTwitterImage(Model uiModel) {
        UserAccount account = AccountUtils.getLoginUserAccount();
        logger.debug("==>MyAccountController.useTwitterImage(), userId=" + account.getUserId());
        account.setConnections(accountService.getConnectionsByUserId(account.getUserId()));
        if (account.isHasTwitterConnection()){
            accountService.updateImageUrl(account.getUserId(), account.getTwitterConnection().getImageUrl());
        }
        return "redirect:/myAccount";
    }

    @RequestMapping(value = "/useFacebookImage", method = RequestMethod.GET, produces = "text/html")
    public String useFacebookImage(Model uiModel) {
        UserAccount account = AccountUtils.getLoginUserAccount();
        logger.debug("==>MyAccountController.useFacebookImage(), userId=" + account.getUserId());
        account.setConnections(accountService.getConnectionsByUserId(account.getUserId()));
        if (account.isHasFacebookConnection()){
            accountService.updateImageUrl(account.getUserId(), account.getFacebookConnection().getImageUrl());
        }
        return "redirect:/myAccount";
    }

}
