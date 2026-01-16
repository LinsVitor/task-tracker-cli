package com.tasktracker.util;

import com.tasktracker.model.Status;
import com.tasktracker.model.Task;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Task task = new Task();
        String quotedRegex = "\"\\s*:\\s*\"([^\"]*)";
        String unquotedRegex = "\"\\s*:\\s*\"?([^\",}\\s]+)\"?";

        Pattern id = Pattern.compile("Id" + unquotedRegex);
        Pattern description = Pattern.compile("Description" + quotedRegex);
        Pattern status = Pattern.compile("Status" + quotedRegex);
        Pattern createdAt = Pattern.compile("CreatedAt" + quotedRegex);
        Pattern updatedAt = Pattern.compile("UpdatedAT" + unquotedRegex);

        Matcher matcher = id.matcher(json);
        if (matcher.find()) {
            task.setId(Integer.parseInt(matcher.group(1)));
        }
        matcher = description.matcher(json);
        if (matcher.find()) {
            task.setDescription(matcher.group(1));
        }
        matcher = status.matcher(json);
        if (matcher.find()) {
            task.setStatus(Status.valueOf(matcher.group(1)));
        }
        matcher = createdAt.matcher(json);
        if (matcher.find()) {
            task.setCreatedAt(LocalDateTime.parse(matcher.group(1)));
        }
        matcher = updatedAt.matcher(json);
        if (matcher.find()) {
            task.setUpdatedAt(LocalDateTime.parse(matcher.group(1)));
        }

        return task;
    }
}
