package com.riddlin.mail;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comfirm.alphamail.services.client.AlphaMailService;
import com.comfirm.alphamail.services.client.AlphaMailServiceException;
import com.comfirm.alphamail.services.client.entities.EmailContact;
import com.comfirm.alphamail.services.client.entities.EmailMessagePayload;
import com.comfirm.alphamail.services.client.entities.ServiceIdentityResponse;

/**
 * Abstract super class for all email sender classes. Using Alpha Mail as
 * underline email service provider.
 */
public class AbstractMailSender {
    final static Logger logger = LoggerFactory.getLogger(AbstractMailSender.class);

    private final AlphaMailService mailService;
    private int projectId;

    private String adminName;
    private String adminEmail;
    
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Inject
    public AbstractMailSender(AlphaMailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Call Alpha Mail service API to send payload object.
     * @param receiver
     * @param payload
     * @return response from Alpha Mail service
     * @throws AlphaMailServiceException
     */
    protected ServiceIdentityResponse doSend(EmailContact sender, EmailContact receiver, Object payload) 
            throws AlphaMailServiceException {
        EmailMessagePayload message = new EmailMessagePayload().setProjectId(getProjectId()).setReceiverId(0)
                .setSender(sender).setReceiver(receiver).setBodyObject(payload);
        ServiceIdentityResponse response = mailService.queue(message);
        logger.debug("send email content ='"+payload.toString());
        logger.info(String.format("Send email to %s, result is %s ", receiver.getEmail(), response.getResult()));
        return response;
    }
}
