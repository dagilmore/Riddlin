package com.riddler.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.riddler.app.domain.account.AccountService;
import com.riddler.app.domain.account.AccountServiceImpl;
import com.riddler.app.domain.account.UserAccountRepository;
import com.riddler.app.domain.account.UserAdminService;
import com.riddler.app.domain.account.UserAdminServiceImpl;
import com.riddler.app.domain.account.UserSocialConnectionRepository;
import com.riddler.app.domain.post.BlogPostRepository;
import com.riddler.app.domain.post.BlogPostService;
import com.riddler.app.domain.post.BlogPostServiceImpl;
import com.riddler.app.domain.post.CommentPostRepository;
import com.riddler.app.domain.post.CommentPostService;
import com.riddler.app.domain.post.CommentPostServiceImpl;
import com.riddler.app.domain.post.SlidePostRepository;
import com.riddler.app.domain.post.SlidePostService;
import com.riddler.app.domain.post.SlidePostServiceImpl;
import com.riddler.app.domain.system.CounterService;
import com.riddler.app.domain.system.CounterServiceImpl;

@Configuration
class MainAppConfig {
    @Inject
    private Environment environment;

    //Repository beans injected from MongoConfig
    @Inject
    private UserAccountRepository accountRepository;
    @Inject
    private BlogPostRepository blogPostRepository;
    @Inject
    private CommentPostRepository commentPostRepository;
    @Inject
    private SlidePostRepository slidePostRepository;
    @Inject
    private UserSocialConnectionRepository userSocialConnectionRepository;

    //Application Service beans
    @Bean
    public AccountService accountService(MongoTemplate mongoTemplate, UserAccountRepository accountRepository,
                    UserSocialConnectionRepository userSocialConnectionRepository) {
        AccountServiceImpl service = new AccountServiceImpl(accountRepository, userSocialConnectionRepository,
                userAdminService(mongoTemplate));
        return service;
    }

    @Bean
    public BlogPostService blogPostService(MongoTemplate mongoTemplate) {
        BlogPostServiceImpl service = new BlogPostServiceImpl(accountRepository, blogPostRepository, 
                commentPostRepository, userAdminService(mongoTemplate), counterService(mongoTemplate));
        return service;
    }

    @Bean
    public CommentPostService commentPostService(MongoTemplate mongoTemplate) {
        CommentPostServiceImpl service = new CommentPostServiceImpl(accountRepository, blogPostRepository, 
                commentPostRepository, userAdminService(mongoTemplate), counterService(mongoTemplate));
        return service;
    }
    
    @Bean
    public SlidePostService slidePostService(MongoTemplate mongoTemplate) {
        SlidePostServiceImpl service = new SlidePostServiceImpl(accountRepository, slidePostRepository, 
                userAdminService(mongoTemplate), counterService(mongoTemplate));
        return service;
    }

    @Bean
    public UserAdminService userAdminService(MongoTemplate mongoTemplate) {
        return new UserAdminServiceImpl(accountRepository, counterService(mongoTemplate));
    }

    @Bean
    public CounterService counterService(MongoTemplate mongoTemplate) {
        return new CounterServiceImpl(mongoTemplate);
    }

}
