package com.riddler.app.domain.account;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB Repository for RememberMeToken entity.
 */
public interface RememberMeTokenRepository extends MongoRepository<RememberMeToken, String>{
    
    RememberMeToken findBySeries(String series);
    
    List<RememberMeToken> findByUsername(String username);
}
