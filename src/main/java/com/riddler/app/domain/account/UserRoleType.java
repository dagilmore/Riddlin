package com.riddler.app.domain.account;

import org.springframework.security.core.GrantedAuthority;

/**
 * Security role type for UserAccount.
 */
public enum UserRoleType implements GrantedAuthority{
    ROLE_ADMIN,  // can manage user account, all posts
    ROLE_AUTHOR, // can manage own posts
    ROLE_USER   // can edit own comment, can edit own profile
    ;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
