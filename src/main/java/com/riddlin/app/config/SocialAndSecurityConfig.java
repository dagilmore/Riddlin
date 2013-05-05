package com.riddlin.app.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.OAuth1AuthenticationService;
import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.riddlin.app.domain.account.AccountService;
import com.riddlin.app.domain.account.AccountUtils;
import com.riddlin.app.domain.account.MongoPersistentTokenRepositoryImpl;
import com.riddlin.app.domain.account.MongoUsersConnectionRepositoryImpl;
import com.riddlin.app.domain.account.RememberMeTokenRepository;
import com.riddlin.app.domain.account.UserAccount;
import com.riddlin.app.domain.account.UserAdminService;
import com.riddlin.app.domain.account.UserSocialConnectionRepository;
import com.riddlin.app.web.AutoConnectionSignUp;

@Configuration
public class SocialAndSecurityConfig {
    @Inject
    private Environment environment;

    @Inject
    private AccountService accountService;

    @Inject UserAdminService userAdminService;
    
    @Inject
    private AuthenticationManager authenticationManager;
    
    @Inject
    private RememberMeTokenRepository rememberMeTokenRepository;

    /**
     * When a new provider is added to the app, register its {@link SocialAuthenticationService} here.
     * 
     * @see FacebookConnectionFactory
     */
    @Bean
    public SocialAuthenticationServiceLocator socialAuthenticationServiceLocator() {
        SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
        
        //add google
        OAuth2ConnectionFactory<Google> googleConnectionFactory = new GoogleConnectionFactory(environment.getProperty("google.clientId"),
                environment.getProperty("google.clientSecret"));
        OAuth2AuthenticationService<Google> googleAuthenticationService = new OAuth2AuthenticationService<Google>(googleConnectionFactory);
        googleAuthenticationService.setScope("https://www.googleapis.com/auth/userinfo.profile"); //get basic info only.
        registry.addAuthenticationService(googleAuthenticationService);

        //add twitter
        OAuth1ConnectionFactory<Twitter> twitterConnectionFactory = new TwitterConnectionFactory(environment.getProperty("twitter.consumerKey"),
                environment.getProperty("twitter.consumerSecret"));
        OAuth1AuthenticationService<Twitter> twitterAuthenticationService = new OAuth1AuthenticationService<Twitter>(twitterConnectionFactory);
        registry.addAuthenticationService(twitterAuthenticationService);

        //add facebook
        OAuth2ConnectionFactory<Facebook> facebookConnectionFactory = new FacebookConnectionFactory(environment.getProperty("facebook.clientId"),
                environment.getProperty("facebook.clientSecret"));
        OAuth2AuthenticationService<Facebook> facebookAuthenticationService = new OAuth2AuthenticationService<Facebook>(facebookConnectionFactory);
        facebookAuthenticationService.setScope(""); //???
        registry.addAuthenticationService(facebookAuthenticationService);

        return registry;
    }

    /**
     * Singleton data access object providing access to connections across all users.
     */
    @Bean
    public UsersConnectionRepository usersConnectionRepository(UserSocialConnectionRepository userSocialConnectionRepository) {
        MongoUsersConnectionRepositoryImpl repository = new MongoUsersConnectionRepositoryImpl(userSocialConnectionRepository,
                socialAuthenticationServiceLocator(), Encryptors.noOpText());
        repository.setConnectionSignUp(autoConnectionSignUp());
        return repository;
    }

    /**
     * Request-scoped data access object providing access to the current user's connections.
     */
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository(UserSocialConnectionRepository userSocialConnectionRepository) {
        UserAccount user = AccountUtils.getLoginUserAccount();
        return usersConnectionRepository(userSocialConnectionRepository).createConnectionRepository(user.getUsername());
    }

    /**
     * A proxy to a request-scoped object representing the current user's primary Google account.
     * 
     * @throws NotConnectedException
     *             if the user is not connected to Google.
     */
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Google google(UserSocialConnectionRepository userSocialConnectionRepository) {
        Connection<Google> google = connectionRepository(userSocialConnectionRepository).findPrimaryConnection(Google.class);
        return google != null ? google.getApi() : new GoogleTemplate();
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)   
    public Facebook facebook(UserSocialConnectionRepository userSocialConnectionRepository) {
        Connection<Facebook> facebook = connectionRepository(userSocialConnectionRepository).findPrimaryConnection(Facebook.class);
        return facebook != null ? facebook.getApi() : new FacebookTemplate();
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)   
    public Twitter twitter(UserSocialConnectionRepository userSocialConnectionRepository) {
        Connection<Twitter> twitter = connectionRepository(userSocialConnectionRepository).findPrimaryConnection(Twitter.class);
        return twitter != null ? twitter.getApi() : new TwitterTemplate();
    }

    @Bean
    public ConnectionSignUp autoConnectionSignUp() {
        return new AutoConnectionSignUp(accountService);
    }

    @Bean
    public SocialAuthenticationFilter socialAuthenticationFilter(UserSocialConnectionRepository userSocialConnectionRepository) {
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(authenticationManager, userAdminService,
                usersConnectionRepository(userSocialConnectionRepository), socialAuthenticationServiceLocator());
        filter.setFilterProcessesUrl("/signin");
        filter.setSignupUrl(null); 
        filter.setConnectionAddedRedirectUrl("/myAccount");
        filter.setPostLoginUrl("/myAccount"); //??? Remove it?
        filter.setRememberMeServices(rememberMeServices());
        return filter;
    }

    @Bean
    public SocialAuthenticationProvider socialAuthenticationProvider(UserSocialConnectionRepository userSocialConnectionRepository){
        return new SocialAuthenticationProvider(usersConnectionRepository(userSocialConnectionRepository), userAdminService);
    }
    
    @Bean
    public LoginUrlAuthenticationEntryPoint socialAuthenticationEntryPoint(){
        return new LoginUrlAuthenticationEntryPoint("/signin");
    }

    @Bean
    public RememberMeServices rememberMeServices(){
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
                        environment.getProperty("application.key"), userAdminService, persistentTokenRepository());
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }
    
    @Bean 
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider = 
                        new RememberMeAuthenticationProvider(environment.getProperty("application.key"));
        return rememberMeAuthenticationProvider; 
    }

    @Bean 
    public PersistentTokenRepository persistentTokenRepository() {
        return new MongoPersistentTokenRepositoryImpl(rememberMeTokenRepository);
    }
}
