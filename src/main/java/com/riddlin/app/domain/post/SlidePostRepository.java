package com.riddlin.app.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SlidePostRepository extends MongoRepository<SlidePost, String>{
    /**
     * Gets SlidePost by published path.
     * 
     * @param path
     * @return
     */
    SlidePost findByPublishedPath(String path);
    
    /**
     * Gets all SlidePost objects for a specific user.
     * 
     * @param authorId
     * @param pageable
     * @return
     */
    Page<SlidePost> findByAuthorId(String authorId, Pageable pageable);
}
