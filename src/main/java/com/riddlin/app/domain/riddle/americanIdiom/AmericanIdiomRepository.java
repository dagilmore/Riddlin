package com.riddlin.app.domain.riddle.americanIdiom;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AmericanIdiomRepository extends MongoRepository<AmericanIdiom, Long> {

    List findAll();

    AmericanIdiom findAmericanIdiomById(Long americanIdiomId);

}