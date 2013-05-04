package com.riddler.app.domain.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * MongoDB Repository for UserAccount entity.
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String>{
    
    UserAccount findByUserId(String userId);
    
    Page<UserAccount> findAllOrderByUserId(Pageable pageable);
}
