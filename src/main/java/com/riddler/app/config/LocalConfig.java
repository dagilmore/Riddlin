
package com.riddler.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.riddler.app.domain.post.CommentPost;
import com.riddler.app.web.CommentNotificationSender;
import com.riddler.app.web.ContactMessageSender;
import com.riddler.app.web.blog.ContactForm;

@Configuration
@Profile("local")
@PropertySource(value={"classpath:localConfig.properties"})
public class LocalConfig {
    @Bean
    public ContactMessageSender contactMessageSender() {
        return new ContactMessageSender(){
            public void send(ContactForm contact){
                System.out.println(String.format("Send email message to jiwhiz. From  %s: \" %s \""
                                , contact.getName(), contact.getMessage()));
            }
        };
    }

    @Bean
    public CommentNotificationSender commentNotificationSender() {
        return new CommentNotificationSender(){
            public void send(CommentPost comment){
                System.out.println(String.format("Send email message to author. %s posted a comment to your blog '%s': \" %s \""
                                , comment.getAuthorAccount().getDisplayName(), comment.getBlogPost().getTitle(), comment.getContent()));
            }
        };
    }

}