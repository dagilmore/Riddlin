package com.riddler.app.domain.account;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import com.riddler.app.domain.BaseEntity;

/**
 * Entity for RememberMe token.
 */
@SuppressWarnings("serial")
@Document(collection = "RememberMeToken")
public class RememberMeToken extends BaseEntity{

    private String username;
    
    @Indexed
    private String series;
    
    private String tokenValue;
    
    private Date date;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RememberMeToken(){
        
    }
    
    public RememberMeToken(PersistentRememberMeToken token){
        this.series = token.getSeries();
        this.username = token.getUsername();
        this.tokenValue = token.getTokenValue();
        this.date = token.getDate();
    }
    
    @Override
    public String toString() {
        return String.format("RememberMeToken{username:'%s'; tokenValue:'%s'}", getUsername(), getTokenValue());
    }

}
