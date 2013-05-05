package com.riddlin.app.domain.account;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionData;


/**
 * Application Service interface for user account. Provide functions based on use cases.
 * 
 */
public interface AccountService {
    
    /**
     * Gets UserAccount object by user login userId.
     * 
     * @param userId
     * @return null if not found
     */
    UserAccount findByUserId(String userId);

    /**
     * Gets all users in the database.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     * 
     * @return
     */
    List<UserAccount> getAllUsers();
    
    /**
     * Gets all users in the database page by page.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     *  
     * @param pageable
     * @return
     */
    Page<UserAccount> getAllUsers(Pageable pageable);

    /**
     * Gets all social connection records for specific user.
     * 
     * @param userId
     * @return
     */
    List<UserSocialConnection> getConnectionsByUserId(String userId);
    
    /**
     * Creates a new UserAccount with user social network account Connection Data
     * 
     * @param data
     * @return
     */
    UserAccount createUserAccount(ConnectionData data);

    /**
     * Updates user's profile data, like email, display name, etc.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     * 
     * @param userId
     * @param displayName
     * @param email
     * @param webSite
     * @param imageUrl
     * @return
     * @throws UsernameNotFoundException
     * 
     */
    UserAccount updateProfile(String userId, String displayName, String email, String webSite, String imageUrl)
            throws UsernameNotFoundException;

    /**
     * Updates user's profile data, like email, display name, etc.
     * SECURITY: Current logged in user must have ROLE_USER.
     * 
     * @param userId
     * @param displayName
     * @param email
     * @param webSite
     * @return
     * @throws UsernameNotFoundException
     */
    UserAccount updateProfile(String userId, String displayName, String email, String webSite)
            throws UsernameNotFoundException;

    /**
     * Updates user's profile photo image URL.
     * SECURITY: Current logged in user must have ROLE_USER.
     * 
     * @param userId
     * @param imageUrl
     * @return
     */
    UserAccount updateImageUrl(String userId, String imageUrl);
    
    /**
     * Set user account to locked or unlocked.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     * 
     * @param userId
     * @param locked
     */
    void lockAccount(String userId, boolean locked);

    /**
     * Set user account to trusted or untrusted.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     * 
     * @param userId
     * @param trusted
     */
    void trustAccount(String userId, boolean trusted);

    /**
     * Add Author role to user account or remove Author role from user account.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     * 
     * @param userId
     * @param isAuthor
     */
    void setAuthor(String userId, boolean isAuthor);
}
