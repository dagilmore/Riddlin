package com.riddler.app.domain.riddler;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionData;


/**
 * Application Service interface for riddles.
 * 
 */
public interface RiddleService {
    
    /**
     * Gets Riddle object by string id
     * 
     * @param riddleId
     * @return null if not found
     */
    Riddle findByRiddleId(String riddleId);

    /**
     * Gets all riddles in the database.
     * 
     * @return List<Riddle>
     */
    List<Riddle> getAllRiddles();
    
    /**
     * Gets all riddles in the database.
     *  
     * @param pageable
     * @return
     */
    Page<UserAccount> getAllUsers(Pageable pageable);


    
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
