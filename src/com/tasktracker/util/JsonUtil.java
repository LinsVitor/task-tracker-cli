package com.tasktracker.util;

import com.tasktracker.model.Task;

public class JsonUtil {
    public static String toJson(Task task) {
        if (task.getUpdatedAt() == null) {
            return """
                {
                  "Id": %d,
                  "Description": "%s",
                  "Status": "%s",
                  "CreatedAt": "%s",
                  "UpdatedAt": %s
                }
                """.formatted(task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
        }
        return """
                {
                  "Id": %d,
                  "Description": "%s",
                  "Status": "%s",
                  "CreatedAt": "%s",
                  "UpdatedAt": "%s"
                }
                """.formatted(task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
    }
}
