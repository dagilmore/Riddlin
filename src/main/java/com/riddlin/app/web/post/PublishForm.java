package com.riddlin.app.web.post;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.riddlin.app.domain.post.BlogPost;

public class PublishForm {
    private String id;
    
    @NotEmpty
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PublishForm(){
        
    }
    
    public PublishForm(BlogPost blog){
        this.id = blog.getId();
        if (StringUtils.hasText(blog.getPublishedPath())){
            path = blog.getPublishedPath();
        } else {
            path = buildPath(blog.getTitle());
        }
    }

    private String buildPath(String title) {
        return title.replace(' ', '_');
    }
    
    public String getYearMonthPath(){
        return "/"+Calendar.getInstance().get(Calendar.YEAR)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/";
    }
}
