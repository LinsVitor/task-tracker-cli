package com.tasktracker.util;

import com.tasktracker.model.Status;
import com.tasktracker.model.Task;

import java.time.LocalDateTime;

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

    public static Task fromJson(String json) {
        json = json.replace("{", "").replace("}", "");
        String[] fileds = json.split(",");
        Task task = new Task();
        task.setId(Long.parseLong(fileds[0].split(":")[1].strip()));
        task.setDescription(fileds[1].split(":")[1].replace("\"", "").strip());
        task.setStatus(Status.valueOf(fileds[2].split(":")[1].replace("\"", "").strip()));
        task.setCreatedAt(LocalDateTime.parse(fileds[3].split(" ")[2].replace("\"", "").strip()));
        String updatedAt = fileds[4].split(" ")[2].replace("\"", "").strip();
        if (updatedAt.contains("null")) {
            task.setUpdatedAt(null);
        }
        else {
            task.setUpdatedAt(LocalDateTime.parse(updatedAt));
        }
        return task;
    }
}
