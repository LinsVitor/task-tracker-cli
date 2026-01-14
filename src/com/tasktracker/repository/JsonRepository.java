package com.tasktracker.repository;

import com.tasktracker.model.Status;
import com.tasktracker.model.Task;
import com.tasktracker.util.JsonUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonRepository implements TaskRepository{
    private static final File file = new File("data/tasks.json");

    @Override
    public void add(Task task) {
        try {
            if (file.exists() && file.length() > 0) {
                List<Task> tasks = new ArrayList<>();
                tasks.add(new Task(1, "testing"));
                tasks.add(new Task(1, "testing"));

                try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write("[");
                    bw.newLine();
                    for (Task tk : tasks) {
                        bw.write("  " + JsonUtil.toJson(tk).concat(","));
                        bw.newLine();
                    }
                    bw.write("  " + JsonUtil.toJson(task));
                    bw.newLine();
                    bw.write("]");
                    bw.flush();
                }
            }
            else {
                new File("data").mkdir();
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write("[");
                    bw.newLine();
                    bw.write("  " + JsonUtil.toJson(task));
                    bw.newLine();
                    bw.write("]");
                    bw.flush();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(long id, Task task) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Task findById(long id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public List<Task> findByStatus(Status status) {
        return List.of();
    }

    @Override
    public void markStatus(long id, Status status) {

    }
}
