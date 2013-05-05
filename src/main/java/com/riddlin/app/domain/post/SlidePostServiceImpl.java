package com.riddlin.app.domain.post;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;

import com.riddlin.app.domain.account.UserAccount;
import com.riddlin.app.domain.account.UserAccountRepository;
import com.riddlin.app.domain.account.UserAdminService;
import com.riddlin.app.domain.system.CounterService;

/**
 * Implementation for SlidePostService.
 */
public class SlidePostServiceImpl extends AbstractPostServiceImpl implements SlidePostService{
    final static Logger logger = LoggerFactory.getLogger(SlidePostServiceImpl.class);
    
    private final SlidePostRepository slidePostRepository;

    @Inject
    public SlidePostServiceImpl(UserAccountRepository accountRepository, SlidePostRepository slidePostRepository, 
            UserAdminService userAdminService, CounterService counterService) {
        super(accountRepository, userAdminService, counterService);
        this.slidePostRepository = slidePostRepository;
    }

    /*
     * (non-Javadoc)
     * @see com.riddlin.app.domain.post.SlidePostService#getSlideByPublishedPath(java.lang.String)
     */
    @Override
    public SlidePost getSlideByPublishedPath(String path) {
        return this.slidePostRepository.findByPublishedPath(path);
    }

    /*
     * (non-Javadoc)
     * @see com.riddlin.app.domain.post.SlidePostService#getSlidePostsForCurrentUser(org.springframework.data.domain.Pageable)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public Page<SlidePost> getSlidePostsForCurrentUser(Pageable pageable){
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), 
                new Sort(Sort.Direction.DESC, "createdTime"));
        UserAccount account = userAdminService.getCurrentUser();
        Page<SlidePost> slideList = slidePostRepository.findByAuthorId(
                    account.getUserId(), pageable);
        
        for (SlidePost slide : slideList){
            slide.setVisits(counterService.getSlidePostVisitCount(slide.getId()));
        }
        return slideList;
    }

    /*
     * (non-Javadoc)
     * @see com.riddlin.app.domain.post.SlidePostService#getSlideById(java.lang.String)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')") //TODO check user is the author of the slide 
    public SlidePost getSlideById(String id) {
        return this.slidePostRepository.findOne(id);
    }

    /*
     * (non-Javadoc)
     * @see com.riddlin.app.domain.post.SlidePostService#createSlide(com.riddlin.app.domain.post.StyleType, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public SlidePost createSlide(StyleType type, String title, String content, String path) {
        UserAccount account = userAdminService.getCurrentUser();
        SlidePost slidePost = new SlidePost(account.getUserId(), type, title, content, path);
        slidePostRepository.save(slidePost);
        return slidePost;
    }

    /*
     * (non-Javadoc)
     * @see com.riddlin.app.domain.post.SlidePostService#updateSlide(com.riddlin.app.domain.post.SlidePost)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_AUTHOR')") //TODO check user is the author of the slide 
    public SlidePost updateSlide(SlidePost slidePost) {
        return slidePostRepository.save(slidePost);
    }

}
