package com.kvmvkxnt.tasktracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
  private final File taskFile;
  private final ObjectMapper mapper;
  private List<Task> tasks;

  public TaskManager() {
    this(getDefaultFilePath());
  }

  public TaskManager(File file) {
    this.taskFile = file;
    this.mapper = new ObjectMapper();
    this.mapper.registerModule(new JavaTimeModule());
    File dir = file.getParentFile();
    if (!dir.exists()) dir.mkdirs();
    this.tasks = loadTasks();
  }

  private static File getDefaultFilePath() {
    return new File(
        System.getProperty("user.home")
            + File.separator
            + ".local"
            + File.separator
            + "share"
            + File.separator
            + "task-cli"
            + File.separator
            + "tasks.json");
  }

  private List<Task> loadTasks() {
    if (!taskFile.exists() || taskFile.length() == 0) {
      return new ArrayList<>();
    }
    try {
      return mapper.readValue(taskFile, new TypeReference<List<Task>>() {});
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public void saveTasks() {
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(taskFile, tasks);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Task> getAllTasks() {
    return tasks;
  }

  public Task addTask(String description) {
    int newId = tasks.stream().mapToInt(task -> task.id).max().orElse(0) + 1;
    LocalDateTime createdAndUpdatedTimestamp = LocalDateTime.now();
    Task newTask =
        new Task(newId, description, 0, createdAndUpdatedTimestamp, createdAndUpdatedTimestamp);
    tasks.add(newTask);
    saveTasks();
    return newTask;
  }

  public Task findById(int id) {
    return tasks.stream().filter(task -> task.id == id).findFirst().orElse(null);
  }

  public boolean deleteTask(int id) {
    boolean removed = tasks.removeIf(task -> task.id == id);
    if (removed) {
      saveTasks();
    }
    return removed;
  }

  public boolean updateTask(int id, String description) {
    Task task = findById(id);
    if (task != null) {
      task.description = description;
      task.updatedAt = LocalDateTime.now();
      saveTasks();
      return true;
    } else {
      return false;
    }
  }

  public boolean updateStatus(int id, int status) {
    Task task = findById(id);
    if (task != null) {
      task.status = status;
      task.updatedAt = LocalDateTime.now();
      saveTasks();
      return true;
    } else {
      return false;
    }
  }

  public List<Task> listByStatus(Integer status) {
    return tasks.stream().filter(task -> task.status == status).collect(Collectors.toList());
  }
}
