package com.riddlin.app.domain.riddle;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RiddleRepository extends MongoRepository<Riddle, String> {

    List findAll();

}