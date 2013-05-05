package com.riddlin.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.riddlin.app.domain.account.AccountService;
import com.riddlin.app.domain.account.AccountServiceImpl;
import com.riddlin.app.domain.account.UserAccountRepository;
import com.riddlin.app.domain.account.UserAdminService;
import com.riddlin.app.domain.account.UserAdminServiceImpl;
import com.riddlin.app.domain.account.UserSocialConnectionRepository;
import com.riddlin.app.domain.riddle.RiddleService;
import com.riddlin.app.domain.riddle.RiddleServiceImpl;
import com.riddlin.app.domain.riddle.RiddleRepository;
import com.riddlin.app.domain.system.CounterService;
import com.riddlin.app.domain.system.CounterServiceImpl;

@Configuration
class MainAppConfig {
    @Inject
    private Environment environment;

    //Repository beans injected from MongoConfig
    @Inject
    private UserAccountRepository accountRepository;
    @Inject
    private UserSocialConnectionRepository userSocialConnectionRepository;
    @Inject
    private RiddleRepository riddleRepository;

    //Application Service beans
    @Bean
    public AccountService accountService(MongoTemplate mongoTemplate, UserAccountRepository accountRepository,
                    UserSocialConnectionRepository userSocialConnectionRepository) {
        AccountServiceImpl service = new AccountServiceImpl(accountRepository, userSocialConnectionRepository,
                userAdminService(mongoTemplate));
        return service;
    }

    @Bean
    public UserAdminService userAdminService(MongoTemplate mongoTemplate) {
        return new UserAdminServiceImpl(accountRepository, counterService(mongoTemplate));
    }

    @Bean
    public RiddleService riddleService() {
        return new RiddleServiceImpl(riddleRepository);
    }

    @Bean
    public CounterService counterService(MongoTemplate mongoTemplate) {
        return new CounterServiceImpl(mongoTemplate);
    }

}
