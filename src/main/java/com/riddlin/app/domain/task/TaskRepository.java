package com.riddlin.app.domain.task;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

    List findAll();

    Task findTaskById(String taskId);

}