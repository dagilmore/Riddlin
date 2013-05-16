package com.riddlin.app.domain.task;

import java.util.List;

public interface TaskService {

    List getAllTasks();

    Task findTaskById(String taskId);

    Task create(Task task);
}