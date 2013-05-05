package com.riddlin.app.domain.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountUtils {
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static UserAccount getLoginUserAccount() {
        if (getAuthentication() != null && getAuthentication().getPrincipal() instanceof UserAccount) {
            return (UserAccount)getAuthentication().getPrincipal();
        }
        return null;
    }

    public static String getLoginUserId() {
        UserAccount account = getLoginUserAccount();
        return (account == null) ? null : account.getUserId();
    }

    private AccountUtils() {}
}
