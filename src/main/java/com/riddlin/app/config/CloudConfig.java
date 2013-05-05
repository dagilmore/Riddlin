
package com.riddlin.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.comfirm.alphamail.services.client.AlphaMailService;
import com.comfirm.alphamail.services.client.DefaultAlphaMailService;
import com.riddlin.app.web.CommentNotificationSender;
import com.riddlin.app.web.CommentNotificationSenderImpl;
import com.riddlin.app.web.ContactMessageSender;
import com.riddlin.app.web.ContactMessageSenderImpl;


@Configuration
@Profile("cloud")
@PropertySource(value={"classpath:cloudConfig.properties", "classpath:alphamail.properties"})
public class CloudConfig {
    @Inject
    private Environment environment;

    @Bean
    public AlphaMailService alphaMailService() {
        DefaultAlphaMailService mailService = new DefaultAlphaMailService();
        mailService.setServiceUrl(environment.getProperty("alphamail.url"));
        mailService.setApiToken(environment.getProperty("alphamail.token"));
        return mailService;
    }

    @Bean
    public ContactMessageSender contactMessageSender() {
        ContactMessageSenderImpl sender = new ContactMessageSenderImpl(alphaMailService());
        sender.setProjectId(environment.getProperty("alphamail.project.contactMessage.id", int.class));
        sender.setAdminName(environment.getProperty("alphamail.adminName"));
        sender.setAdminEmail(environment.getProperty("alphamail.adminEmail"));
        return sender;
    }

    @Bean
    public CommentNotificationSender commentNotificationSender() {
        CommentNotificationSenderImpl sender = new CommentNotificationSenderImpl(alphaMailService());
        sender.setProjectId(environment.getProperty("alphamail.project.comment.notification.id", int.class));
        sender.setAdminName(environment.getProperty("alphamail.adminName"));
        sender.setAdminEmail(environment.getProperty("alphamail.adminEmail"));
        return sender;
    }
}