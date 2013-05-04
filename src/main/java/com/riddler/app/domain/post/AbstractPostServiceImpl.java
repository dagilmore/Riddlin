package com.riddler.app.domain.post;

import javax.inject.Inject;

import org.springframework.security.access.AccessDeniedException;

import com.riddler.app.domain.account.UserAccount;
import com.riddler.app.domain.account.UserAccountRepository;
import com.riddler.app.domain.account.UserAdminService;
import com.riddler.app.domain.system.CounterService;

/**
 * Super class for all post service implementation classes.
 */
public abstract class AbstractPostServiceImpl {
    
    protected final UserAccountRepository accountRepository;
    protected final UserAdminService userAdminService;
    protected final CounterService counterService;

    @Inject
    public AbstractPostServiceImpl(UserAccountRepository accountRepository, UserAdminService userAdminService, CounterService counterService) {
        this.accountRepository = accountRepository;
        this.userAdminService = userAdminService;
        this.counterService = counterService;
    }
    
    protected void loadAuthorProfile(Iterable<? extends AbstractPost> postList) {
        for (AbstractPost post : postList) {
            loadAuthorProfile(post);
        }
    }

    protected void loadAuthorProfile(AbstractPost post) {
        if (post != null){
            post.setAuthorAccount(accountRepository.findByUserId(post.getAuthorId()));
        }
    }

    /**
     * Throws AccessDeniedException if current user is not the author of the post.
     * 
     * @param post
     * @throws AccessDeniedException
     */
    protected void checkIsAuthorOfPost(AbstractPost post) throws AccessDeniedException {
        if (post == null || !post.getAuthorId().equals(userAdminService.getUserId())){
            throw new AccessDeniedException("Cannot access the post by current user.");
        }
    }
    
    /**
     * Throws AccessDeniedException if current user is not admin and not the author of the post.
     * 
     * @param post
     * @throws AccessDeniedException
     */
    protected void checkIsAdminOrAuthorOfPost(AbstractPost post) throws AccessDeniedException{
        UserAccount currentUser = userAdminService.getCurrentUser();
        if (currentUser == null){
            throw new AccessDeniedException("No logged in user.");
        }
        
        if (currentUser.isAdmin()){
            return;
        }
        
        checkIsAuthorOfPost(post);
    }

}
