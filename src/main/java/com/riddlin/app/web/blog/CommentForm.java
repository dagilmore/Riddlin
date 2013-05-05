package com.riddlin.app.web.blog;

import org.hibernate.validator.constraints.NotEmpty;

import com.riddlin.app.domain.post.CommentPost;

public class CommentForm {
    private String id;
    
    @NotEmpty
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentForm() {

    }

    public CommentForm(CommentPost comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }

}
