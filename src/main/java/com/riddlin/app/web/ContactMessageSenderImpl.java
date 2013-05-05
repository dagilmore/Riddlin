package com.riddlin.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comfirm.alphamail.services.client.AlphaMailService;
import com.comfirm.alphamail.services.client.AlphaMailServiceException;
import com.comfirm.alphamail.services.client.entities.EmailContact;
import com.riddlin.app.web.blog.ContactForm;
import com.riddlin.mail.AbstractMailSender;

/**
 * Implementation for ContactMessageSender. Using Alpha Mail.
 */
public class ContactMessageSenderImpl extends AbstractMailSender implements ContactMessageSender {
    final static Logger logger = LoggerFactory.getLogger(ContactMessageSenderImpl.class);
    
	public ContactMessageSenderImpl(AlphaMailService mailService){
        super(mailService);
    }
    
    /* (non-Javadoc)
     * @see com.riddlin.app.web.ContactMessageSender#send(com.riddlin.app.web.ContactForm)
     */
    @Override
    public void send(ContactForm contact) {
        EmailContact sender = new EmailContact(contact.getName(), contact.getEmail());
        EmailContact receiver = new EmailContact(getAdminName(), getAdminEmail());
        
        try {
            doSend(sender, receiver, contact);
        } catch (AlphaMailServiceException e) {
            e.printStackTrace();
            String logMessage = String.format("AlphaMail returned exception: Error Code: %s, Error Message: %s", e
                    .getResponse().getErrorCode(), e.getResponse().getMessage());
            logger.warn(logMessage);
            // TODO display error in UI and let user contact admin?
        }

    }

}
