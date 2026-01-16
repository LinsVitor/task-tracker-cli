package com.tasktracker.repository;

import com.tasktracker.model.Status;
import com.tasktracker.model.Task;
import com.tasktracker.util.JsonUtil;

import java.io.*;
import java.util.*;

public class JsonRepository implements TaskRepository{
    private static final File file = new File("data/tasks.json");
    private static final boolean fileExistAndNotEmpty = file.exists() && file.length() > 0;

    @Override
    public void add(Task task) {
        try {
            if (fileExistAndNotEmpty) {
                List<Task> tasks = findAll();
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
        delete(id);
        add(task);
    }

    @Override
    public void delete(long id) {
        if (fileExistAndNotEmpty) {
            List<Task> tasks = findAll();
            if (tasks.isEmpty()) {
                throw new NoSuchElementException("No tasks found in file");
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("[");
                bw.newLine();
                for (int i = 0; i < tasks.size(); i++) {
                    if (i == tasks.size() - 1 && tasks.get(i).getId() != id) {
                        bw.write("  " + JsonUtil.toJson(tasks.get(i)));
                        bw.newLine();
                    }
                    else {
                        if (tasks.get(i).getId() != id) {
                            bw.write("  " + JsonUtil.toJson(tasks.get(i)).concat(","));
                            bw.newLine();
                        }
                    }
                }
                bw.write("]");
                bw.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Task findById(long id) {
        if (fileExistAndNotEmpty) {
            List<Task> tasks = findAll();
            for (Task task : tasks) {
                if (task.getId() == id) {
                    return task;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public List<Task> findAll() {
        if (fileExistAndNotEmpty) {
            List<Task> tasks = new ArrayList<>();
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    if (line.contains("[")) {
                        line = br.readLine();
                    }
                    if (line.contains("]")) {
                        return tasks;
                    }
                    if (line.isEmpty()) {
                        return tasks;
                    }
                    Task task = JsonUtil.fromJson(line);
                    tasks.add(task);
                    line = br.readLine();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return tasks;
        }
        return null;
    }

    @Override
    public List<Task> findByStatus(Status status) {
        if (fileExistAndNotEmpty) {
            List<Task> tempTask = findAll();
            List<Task> tasksStatus = new ArrayList<>();
            for (Task tk : tempTask) {
                if (tk.getStatus() == status) {
                    tasksStatus.add(tk);
                }
            }
            return tasksStatus;
        }
        return null;
    }
}
