package com.riddler.app.domain.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BlogPostRepository extends MongoRepository<BlogPost, String>{
    
    List<BlogPost> findByPublishedIsTrue(Sort sort);
    
    Page<BlogPost> findByPublishedIsTrueOrderByPublishedTimeDesc(Pageable pageable);
    
    Page<BlogPost> findByPublishedIsTrueAndTagsOrderByPublishedTimeDesc(String tag, Pageable pageable);
    
    Page<BlogPost> findByAuthorId(String authorId, Pageable pageable);
    
    BlogPost findByPublishedYearAndPublishedMonthAndPublishedPath(int year, int month, String path);
    
    List<BlogPost> findByTitleRegexIgnoreCase(String title);
    
    @Query(value="{ 'published' : true }", fields="{ 'content' : 0}")
    List<BlogPost> findAllPublishedPostsWithoutContent();

    @Query(value="{ 'published' : true }", fields="{ 'tags' : 1}")
    List<BlogPost> findAllPublishedPostsIncludeTags();
}
