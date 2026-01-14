package com.tasktracker;

import com.tasktracker.model.Task;

public class Main {
    static void main() {
        Task task = new Task(1L, "Test");
        IO.println(task);
    }
}
