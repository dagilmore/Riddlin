package com.riddlin.app.domain.account;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.util.StringUtils;

import com.riddlin.app.domain.BaseEntity;

/**
 * Domain Entity for user account.
 */
@SuppressWarnings("serial")
@Document(collection = "UserAccount")
public class UserAccount extends BaseEntity implements SocialUserDetails {
    @Indexed
    private String userId;
    
    private UserRoleType[] roles;
    
    private String email;
    
    private String displayName;
    
    private String imageUrl;
    
    private String webSite;
    
    private boolean accountLocked;
    
    private boolean trustedAccount;

    @Transient
    private List<UserSocialConnection> connections;

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }

    public UserRoleType[] getRoles() {
        return roles;
    }

    public void setRoles(UserRoleType[] roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isTrustedAccount() {
        return trustedAccount;
    }

    public void setTrustedAccount(boolean trustedAccount) {
        this.trustedAccount = trustedAccount;
    }

    public List<UserSocialConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<UserSocialConnection> connections) {
        this.connections = connections;
    }

    public UserAccount() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        // No password stored
        return null;
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    public String getWebSiteLink(){
        if (StringUtils.hasText(getWebSite())){
            if (getWebSite().startsWith("http://") || getWebSite().startsWith("https://")){
                return getWebSite();
            }
            return "http://"+getWebSite();
            // add http:// to fix URL 
        }
        return "#";
    }

    public String getNameLink(){
        //TODO link to profile page
        return getWebSiteLink();
    }
    
    public boolean isAuthor(){
        for (UserRoleType role : getRoles()) {
            if (role == UserRoleType.ROLE_AUTHOR){
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(){
        for (UserRoleType role : getRoles()) {
            if (role == UserRoleType.ROLE_ADMIN){
                return true;
            }
        }        
        return false;
    }
    
    public boolean isHasImageUrl(){
        return StringUtils.hasLength(getImageUrl());
    }
    
    // used for account social connection
    private UserSocialConnection getConnection(String providerId) {
        if (this.connections != null){
            for (UserSocialConnection connection : this.connections){
                if (connection.getProviderId().equals(providerId)){
                    return connection;
                }
            }
        }
        return null;
    }

    public UserSocialConnection getGoogleConnection() {
        return getConnection("google");
    }
    
    public boolean isHasGoogleConnection(){
        return getGoogleConnection()  != null;
    }

    public UserSocialConnection getTwitterConnection() {
        return getConnection("twitter");
    }
    
    public boolean isHasTwitterConnection(){
        return getTwitterConnection()  != null;
    }

    public UserSocialConnection getFacebookConnection() {
        return getConnection("facebook");
    }
    
    public boolean isHasFacebookConnection(){
        return getFacebookConnection()  != null;
    }
    
    void updateProfile(String displayName, String email, String webSite){
        setDisplayName(displayName);
        setEmail(email);
        setWebSite(webSite);
    }
    
    @Override
    public String toString() {
        String str = String.format("UserAccount{userId:'%s'; displayName:'%s';roles:[", getUserId(), getDisplayName());
        for (UserRoleType role : getRoles()) {
            str += role.toString() + ",";
        }
        return str + "]}";
    }

}
