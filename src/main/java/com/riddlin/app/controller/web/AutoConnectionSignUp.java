package com.riddlin.app.controller.web;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionSignUp;

import com.riddlin.app.domain.account.AccountService;
import com.riddlin.app.domain.account.UserAccount;

/**
 * Automatically sign up user who is already signin through other social network account (google or twitter).
 * Create a new UserAccount in database, populate user's profile data from provider.
 */
public class AutoConnectionSignUp implements ConnectionSignUp{
    private final AccountService accountService;
    
    @Inject
    public AutoConnectionSignUp(AccountService accountService){
        this.accountService = accountService;
    }
    
    public String execute(Connection<?> connection) {
        ConnectionData data = connection.createData();
        
        UserAccount account = this.accountService.createUserAccount(data);
        
        return account.getUserId();
    }
}
