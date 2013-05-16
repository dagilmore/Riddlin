package com.riddlin.app.domain.task;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskServiceImpl implements TaskService {
    static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List getAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(String taskId) {
        return taskRepository.findTaskById(taskId);
    }

    public Task create(Task task) {
        return (Task)taskRepository.save(task);
    }

}