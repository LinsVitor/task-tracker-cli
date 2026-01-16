package com.tasktracker.service;

import com.tasktracker.exception.ServiceException;
import com.tasktracker.model.Status;
import com.tasktracker.model.Task;
import com.tasktracker.repository.JsonRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TaskService {
    private final static JsonRepository jsonRepo = new JsonRepository();

    private long genId() {
        long id = 1;
        List<Task> tasks = jsonRepo.findAll();
        for (Task task : tasks) {
            if (task.getId() != id) {
                return id;
            }
            id++;
        }
        return id;
    }

    public Task add(String description) {
        if (description.trim().isEmpty()) {
            throw new ServiceException("Description can't be empty.");
        }
        Task task = new Task(genId(), description);
        jsonRepo.add(task);
        return task;
    }

    public void update(long id, String description) {
        if (id == 0 || id < 0) {
            throw new ServiceException("Id must be greater than zero");
        }
        if (description.trim().isEmpty()) {
            throw new ServiceException("Description can't be empty.");
        }
        Task task = jsonRepo.findById(id);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        jsonRepo.update(id, task);
    }
    
    public void delete(long id) {
        if (id == 0 || id < 0) {
            throw new ServiceException("Id must be greater than zero");
        }
        if (jsonRepo.findById(id) == null) {
            throw new ServiceException("No task exists with this id");
        }
        jsonRepo.delete(id);
    }

    public Task find(long id) {
        if (id == 0 || id < 0) {
            throw new ServiceException("Id must be greater than zero");
        }
        Task task = jsonRepo.findById(id);
        if (task == null) {
            throw new ServiceException("No task exists with this id");
        }
        return task;
    }

    public List<Task> list() {
        List<Task> tasks = jsonRepo.findAll();
        if (tasks.isEmpty()) {
            throw new ServiceException("There are no tasks");
        }
        return tasks;
    }

    public List<Task> list(Status status) {
        List<Task> tasks = jsonRepo.findAll();
        if (tasks.isEmpty()) {
            throw new ServiceException("There are no tasks");
        }
        return tasks.stream().filter(task -> task.getStatus() == status).toList();
    }

    public void mark(long id, Status status) {
        if (id == 0 || id < 0) {
            throw new ServiceException("Id must be greater than zero");
        }
        if (Arrays.stream(Status.values()).noneMatch(x -> x == status)) {
            throw new ServiceException("Status not found");
        }
        Task task = jsonRepo.findById(id);
        if (task == null) {
            throw new ServiceException("No task exists with this id");
        }
        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());
        jsonRepo.update(id, task);
    }
}
