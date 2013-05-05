package com.riddlin.app.web.post;

import org.hibernate.validator.constraints.NotEmpty;

import com.riddlin.app.domain.post.SlidePost;
import com.riddlin.app.domain.post.StyleType;

public class SlideForm {
    private String id;
    
    private StyleType type;
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String content;
    
    @NotEmpty
    private String publishedPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StyleType getType() {
        return type;
    }

    public void setType(StyleType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedPath() {
        return publishedPath;
    }

    public void setPublishedPath(String publishedPath) {
        this.publishedPath = publishedPath;
    }

    public SlideForm(){
        
    }
    
    public SlideForm(SlidePost slidePost){
        this.id = slidePost.getId();
        this.type = slidePost.getType();
        this.publishedPath = slidePost.getPublishedPath();
        this.title = slidePost.getTitle();
        this.content = slidePost.getContent();
    }


}
