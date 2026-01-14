package com.tasktracker.util;

import com.tasktracker.model.Task;

public class JsonUtil {
    public static String toJson(Task task) {
        if (task.getUpdatedAt() == null) {
            return "{\"Id\": %s, \"Description\": \"%s\", \"Status\": \"%s\", \"CreatedAt\": \"%s\", \"UpdatedAt\": %s}"
                    .formatted(task.getId(),
                            task.getDescription(),
                            task.getStatus(),
                            task.getCreatedAt(),
                            task.getUpdatedAt());
        }
        return "{\"Id\": %s, \"Description\": \"%s\", \"Status\": \"%s\", \"CreatedAt\": \"%s\", \"UpdatedAt\": \"%s\"}"
                .formatted(task.getId(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getCreatedAt(),
                        task.getUpdatedAt());
    }
}
