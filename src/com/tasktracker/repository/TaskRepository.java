package com.tasktracker.repository;

import com.tasktracker.model.Status;
import com.tasktracker.model.Task;

import java.util.List;

public interface TaskRepository {
    void add(Task task);
    void update(long id, Task task);
    void delete(long id);
    Task findById(long id);
    List<Task> findAll();
    List<Task> findByStatus(Status status);
    void markStatus(long id, Status status);
}
