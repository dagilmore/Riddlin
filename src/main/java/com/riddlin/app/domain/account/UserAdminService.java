package com.riddlin.app.domain.account;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * Domain Service interface for user administration. It also extends SocialUserDetailsService,
 * UserDetailsService and UserIdSource.
 */
public interface UserAdminService extends SocialUserDetailsService, UserDetailsService, UserIdSource{
    
    /**
     * Creates a new UserAccount with user social network account Connection Data.
     * Default has ROLE_USER
     * 
     * @param data
     * @return
     */
    UserAccount createUserAccount(ConnectionData data);

    /**
     * Add Author role to user account or remove Author role from user account.
     * 
     * @param userId
     * @param isAuthor
     */
    void setAuthor(String userId, boolean isAuthor);
    
    /**
     * Override SocialUserDetailsService.loadUserByUserId(String userId) to 
     * return UserAccount.
     */
    UserAccount loadUserByUserId(String userId) throws UsernameNotFoundException;
    
    /**
     * Gets current logged in user. Reload UserAccount object from userId in SecurityContextHolder. 
     * 
     * @return UserAccount object from database for current user
     */
    UserAccount getCurrentUser();

}
