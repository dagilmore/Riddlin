package com.riddler.app.domain.post;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Application Service Interface for BlogPost.
 */
public interface BlogPostService {
    /**
     * Gets all published posts from all authors ordered by publish time.
     * The BlogPost will be pre-loaded with authorAccount and published comments.
     * 
     * @return
     */
    Page<BlogPost> getAllPublishedPosts(Pageable pageable);

    /**
     * Gets first (count) published posts from all authors ordered by publishedTime.
     * The BlogPost will be pre-loaded with authorAccount.
     * 
     * @return
     */
    List<BlogPost> getPublishedPosts(int count);

    /**
     * Gets tag cloud of all published posts.
     *  
     * @return Map of tag (upper case) with count.
     */
    Map<String, Integer> getTagCloud();

    /**
     * Gets published posts that have input tag.
     * The BlogPost will be pre-loaded with authorAccount and published comments.
     * 
     * @param tag
     * @param pageable
     * @return
     */
    Page<BlogPost> getPublishedPostsByTag(String tag, Pageable pageable);
    
    /**
     * Gets BlogPost object by public access path, format is
     * /[year]/[month]/path
     * The BlogPost object will be pre-loaded with authorAccount and published comments. 
     * @param year
     * @param month
     * @param path
     * @return
     */
    BlogPost getPostByPublishedPath(int year, int month, String path);

    /**
     * Gets published BlogPost object by id. The BlogPost object will be pre-loaded 
     * with authorAccount and published comments (also pre-loaded with authorAccount).
     *  
     * @param id
     * @return null if BlogPost object is not published.
     */
    BlogPost getPublishedPostById(String id);

    /**
     * Gets BlogPost object by id. The BlogPost object will be pre-loaded 
     * with authorAccount and all comments (include unpublished). 
     * SECURITY: Current logged in user must have ROLE_ADMIN; Or current user has 
     * ROLE_AUTHOR and is the author of the post.
     * 
     * @param id
     * @return
     */
    BlogPost getPostById(String id);

    /**
     * Gets all posts for current logged in user.
     * SECURITY: Current logged in user must have ROLE_AUTHOR.
     * 
     * @return
     */
    Page<BlogPost> getPostsForCurrentUser(Pageable pageable);

    /**
     * Create a blog post by current logged in user.
     * SECURITY: Current logged in user must have ROLE_AUTHOR.
     * 
     * @param title
     * @param content
     * @return new BlogPost object with author as login user's userId.
     */
    BlogPost createPost(String title, String content, String tagString);

    /**
     * Update blog post content by current logged in user.
     * SECURITY: Current logged in user must have ROLE_AUTHOR and be the author of the BlogPost.
     * 
     * @param id
     * @param content
     * @return
     */
    BlogPost updateContent(String id, String content);
    
    /**
     * Update blog post meta data by current logged in user.
     * SECURITY: Current logged in user must have ROLE_AUTHOR and be the author of the BlogPost.
     * 
     * @param id
     * @param title
     * @param tagString
     * @return
     */
    BlogPost updateMeta(String id, String title, String tagString);
    
    /**
     * Publish blog post by current logged in user.
     * SECURITY: Current logged in user must have ROLE_AUTHOR and be the author of the BlogPost.
     * 
     * @param id
     * @param path
     * @return
     */
    BlogPost publish(String id, String path);
    
    /**
     * Un-Publish blog post by current logged in user.
     * SECURITY: Current logged in user must have ROLE_AUTHOR and be the author of the BlogPost.
     * 
     * @param id
     * @return
     */
    BlogPost unpublish(String id);

}
