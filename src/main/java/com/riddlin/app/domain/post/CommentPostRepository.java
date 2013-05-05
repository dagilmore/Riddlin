package com.riddlin.app.domain.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentPostRepository extends MongoRepository<CommentPost, String>{
    List<CommentPost> findByPostId(String postId, Sort sort);
    
    List<CommentPost> findByPostIdAndPublishedIsTrue(String postId, Sort sort);
    
    List<CommentPost> findByAuthorIdAndPublishedIsTrue(String authorId, Sort sort);
    
    List<CommentPost> findByPublishedIsTrue(Sort sort);
    
    Page<CommentPost> findAll(Pageable pageable);
    
    Page<CommentPost> findByAuthorId(String authorId, Pageable pageable);
}
