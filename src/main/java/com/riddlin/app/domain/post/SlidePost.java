package com.riddlin.app.domain.post;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain Entity for slide post.
 */
@SuppressWarnings("serial")
@Document(collection = "SlidePost")
public class SlidePost extends AbstractPost{
    
    private StyleType type;
    
    private String title;
    
    @Indexed
    private String publishedPath;
    
    @Transient
    private long visits;
    
    public long getVisits() {
        return visits;
    }

    void setVisits(long visits) {
        this.visits = visits;
    }

    public String getPublishedPath() {
        return publishedPath;
    }

    public void setPublishedPath(String publishedPath) {
        this.publishedPath = publishedPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StyleType getType() {
        return type;
    }

    void setType(StyleType type) {
        this.type = type;
    }

    public SlidePost() {
        super();
    }
    
    public SlidePost (String authorId, StyleType type, String title, String content, String path) {
        super(authorId, content);
        this.type = type;
        this.title = title;
        this.publishedPath = path;
    }
    
    public void update(String path, String title, String content) {
        this.publishedPath = path;
        this.title = title;
        setContent(content);
        triggerModified();
    }
    
}
