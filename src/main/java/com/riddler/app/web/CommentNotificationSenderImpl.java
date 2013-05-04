package com.riddler.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.comfirm.alphamail.services.client.AlphaMailService;
import com.comfirm.alphamail.services.client.AlphaMailServiceException;
import com.comfirm.alphamail.services.client.entities.EmailContact;
import com.riddler.app.domain.account.UserAccount;
import com.riddler.app.domain.post.CommentPost;
import com.riddler.mail.AbstractMailSender;

public class CommentNotificationSenderImpl extends AbstractMailSender implements CommentNotificationSender {
    final static Logger logger = LoggerFactory.getLogger(CommentNotificationSenderImpl.class);
    
    public CommentNotificationSenderImpl(AlphaMailService mailService){
        super(mailService);
    }

    /* (non-Javadoc)
     * @see com.riddler.app.web.CommentNotificationSender#send(com.riddler.app.domain.post.CommentPost)
     */
    @Override
    public void send(CommentPost comment) {
    	UserAccount blogAuthor = comment.getBlogPost().getAuthorAccount();
    	
    	EmailContact sender = new EmailContact(comment.getAuthorAccount().getDisplayName(), comment.getAuthorAccount().getEmail());
        EmailContact receiver = new EmailContact(blogAuthor.getDisplayName(), blogAuthor.getEmail());
        EmailContact admin = new EmailContact(getAdminName(), getAdminEmail());
        Payload payload  = new Payload();
        payload.author = comment.getBlogPost().getAuthorAccount().getDisplayName();
        payload.user = comment.getAuthorAccount().getDisplayName();
        payload.post = comment.getBlogPost().getFullPublishedPath();
        payload.comment = comment.getContent();

        try {
            if (StringUtils.isEmpty(blogAuthor.getEmail())){
                logger.info(String.format("Cannot send comment notification email. User %s email address is not set.", blogAuthor.getUserId()));
            } else {
                doSend(sender, receiver, payload);
            }
            
            if (!admin.getEmail().equals(receiver.getEmail())){
                payload.author = "admin";
                doSend(sender, admin, payload);
            }
        } catch (AlphaMailServiceException e) {
            e.printStackTrace();
            String logMessage = String.format("AlphaMail returned exception: Error Code: %s, Error Message: %s", e
                    .getResponse().getErrorCode(), e.getResponse().getMessage());
            logger.warn(logMessage);
            // TODO display error in UI and let user contact admin?
        }

    }
    
    public class Payload {
        public String author;
        public String user;
        public String post;
        public String comment;
        
        public String toString() {
            return String.format("Payload{author:'%s', user:'%s', post:'%s', comment:'%s'", author, user, post, comment);
        }
    }

}
