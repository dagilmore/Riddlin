package com.riddler.app.domain.system;

/**
 * Service interface for counter functions.
 */
public interface CounterService {
    
    long getNextUserIdSequence();
    
    long logVisit();
    
    long logBlogPostVisit(String blogPostId);
    
    long logSlidePostVisit(String slidePostId);
    
    long getVisitCount();
    
    long getBlogPostVisitCount(String blogPostId);
    
    long getSlidePostVisitCount(String slidePostId);

}
