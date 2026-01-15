package com.tasktracker.cli;

import com.tasktracker.model.Status;
import com.tasktracker.model.Task;
import com.tasktracker.service.TaskService;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
    private static final TaskService service = new TaskService();

    public static void command(String command, String method) {
        List<String> methods = Arrays.asList("list", "help");
        if (!command.equals("task-cli")) {
            throw new IllegalArgumentException("Expected task-cli, received " + command);
        }
        if (!methods.contains(method)) {
            throw new IllegalArgumentException("Wrong or non-existent argument");
        }
        switch (method) {
            case "list":
                List<Task> tasks = service.list();
                for (Task tk : tasks) {
                    IO.println("-".repeat(30));
                    IO.println(tk);
                    IO.println();
                }
                break;
            case "help":
                IO.println("""
                        
                        +----------+----------------------------------+------------------------------------+
                        | Command  | Description                      | Example                            |
                        +----------+----------------------------------+------------------------------------+
                        | add      | Add a new task                   | task-cli add "Buy groceries"       |
                        | update   | Update an existing task by ID    | task-cli update 1 "Learn C"        |
                        | delete   | Remove a task                    | task-cli delete 1                  |
                        | find     | Find a task by ID                | task-cli find 1                    |
                        | list     | List all tasks                   | task-cli list                      |
                        | list     | List tasks by status             | task-cli list todo                 |
                        | mark     | Mark the status of a task        | task-cli mark 1 done               |
                        +----------+----------------------------------+------------------------------------+
                        """);
                break;
        }
    }

    public static void command(String command, String method, String action) {
        List<String> methods = Arrays.asList("add", "update", "delete", "find", "list", "mark");
        if (!command.equals("task-cli")) {
            throw new IllegalArgumentException("Expected task-cli, received " + command);
        }
        if (!methods.contains(method)) {
            throw new IllegalArgumentException("Wrong or non-existent argument");
        }
        if (action.isEmpty()) {
            throw new IllegalArgumentException("The text field cannot be empty.");
        }
        switch (method) {
            case "add":
                Task task = service.add(action);
                IO.println("Task added successfully (ID: %s)".formatted(task.getId()));
                break;
            case "delete":
                service.delete(Integer.parseInt(action));
                break;
            case "find":
                task = service.find(Integer.parseInt(action));
                IO.println(task);
                break;
            case "list":
                List<Task> tasks = service.list(Status.valueOf(action.toUpperCase().trim()));
                for (Task tk : tasks) {
                    IO.println("-".repeat(30));
                    IO.println(tk);
                    IO.println();
                }
                break;
        }
    }

    public static void command(String command, String method, String id, String action) {
        List<String> methods = Arrays.asList("update", "mark");
        if (!command.equals("task-cli")) {
            throw new IllegalArgumentException("Expected task-cli, received " + command);
        }
        if (!methods.contains(method)) {
            throw new IllegalArgumentException("Wrong or non-existent argument");
        }
        switch (method) {
            case "mark":
                service.mark(Integer.parseInt(id), Status.valueOf(action.toUpperCase().trim()));
                break;
            case "update":
                service.update(Integer.parseInt(id), action);
                break;
        }
    }
}

