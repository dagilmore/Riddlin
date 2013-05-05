package com.riddlin.app.web.post;

import com.riddlin.app.domain.post.BlogPost;

public class BlogForm {
    private String id;
    
    private String title;
    
    private String content;
    
    private String tagString;
    
    private int publishedYear;

    private int publishedMonth;

    private String publishedPath;
    
    private boolean published;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }


    public String getPublishedPath() {
        return publishedPath;
    }

    public void setPublishedPath(String publishedPath) {
        this.publishedPath = publishedPath;
    }
    
    public boolean isPublished() {
        return published;
    }

    public BlogForm(){
        
    }
    
    public BlogForm(BlogPost blogPost){
        this.id = blogPost.getId();
        this.published = blogPost.isPublished();
        this.publishedYear = blogPost.getPublishedYear();
        this.publishedMonth = blogPost.getPublishedMonth();
        this.publishedPath = blogPost.getPublishedPath();
        this.title = blogPost.getTitle();
        this.content = blogPost.getContent();
        this.tagString = blogPost.getFormatTagString();
    }

    public String getYearMonthPath(){
        return "/"+this.publishedYear+"/"+this.publishedMonth+"/";
    }
}
