package com.riddlin.app.domain.post;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.github.rjeschke.txtmark.Configuration;
import com.github.rjeschke.txtmark.Processor;

/**
 * Domain Entity for blog comment.
 */
@SuppressWarnings("serial")
@Document(collection = "CommentPost")
public class CommentPost extends AbstractPost {
    
    @Indexed
    private String postId;
    
    private boolean spam;

    @Transient
    private BlogPost blogPost;

    public String getPostId() {
        return postId;
    }

    void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isSpam() {
        return spam;
    }

    void setSpam(boolean spam) {
        this.spam = spam;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public CommentPost() {

    }

    public CommentPost(String postId, String userId, String content) {
        super(userId, content);
        this.postId = postId;
    }

    public String getHtmlContent(){
         return Processor.process(getContent(),  Configuration.builder().setSafeMode(true).build());
    }
    
    public String toString() {
        return String.format("Comment{by='%s';on='%s'}", getAuthorId(), getCreatedDateTimeString());
    }
    
}
