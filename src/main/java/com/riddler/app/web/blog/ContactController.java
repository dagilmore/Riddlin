package com.riddler.app.web.blog;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riddler.app.message.Message;
import com.riddler.app.message.MessageType;
import com.riddler.app.web.AbstractPublicPageController;
import com.riddler.app.web.ContactMessageSender;

/**
 * Controller for Contact page. Handle submitting contact message function.
 */
@Controller
public class ContactController extends AbstractPublicPageController {
    @Inject
    private ContactMessageSender contactMessageSender;

    public ContactController() {
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactForm(Model uiModel, HttpServletRequest request) {
        counterService.logVisit();
        uiModel.addAttribute("contactForm", new ContactForm());
        return "site/contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactSubmit(@ModelAttribute("contactForm") @Valid ContactForm contactForm,
            BindingResult bindingResult, Model uiModel, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message(MessageType.ERROR, "Please fill in all the required fields."));
            uiModel.addAttribute("contactForm", contactForm);
            return "site/contact";
        }

        //send email message to me
        contactMessageSender.send(contactForm);

        uiModel.addAttribute("message", new Message(MessageType.SUCCESS, "Hi "+ contactForm.getName()+
                ", your message was received."));
        uiModel.addAttribute("contactForm", new ContactForm());
        return "site/contact";
    }

}
