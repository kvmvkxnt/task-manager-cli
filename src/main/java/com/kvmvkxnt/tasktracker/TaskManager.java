package com.kvmvkxnt.tasktracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {

  private static final String FILE_PATH = "tasks.json";
  private final ObjectMapper mapper = new ObjectMapper();
  private List<Task> tasks;

  public TaskManager() {
    this.tasks = loadTasks();
  }

  private List<Task> loadTasks() {
    File file = new File(FILE_PATH);
    if (!file.exists()) {
      return new ArrayList<>();
    }
    try {
      return mapper.readValue(file, new TypeReference<List<Task>>() {});
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public void saveTasks() {
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), tasks);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Task> getAllTasks() {
    return tasks;
  }

  public void addTask(String description) {
    int newId = tasks.stream().mapToInt(task -> task.id).max().orElse(0) + 1;
    Task newTask = new Task(newId, description, 0);
    tasks.add(newTask);
    saveTasks();
    System.out.println(String.format("Task added successfully (ID: %d)", newId));
  }

  public Task findById(int id) {
    return tasks.stream().filter(task -> task.id == id).findFirst().orElse(null);
  }

  public void deleteTask(int id) {
    boolean removed = tasks.removeIf(task -> task.id == id);
    if (removed) {
      saveTasks();
      System.out.println(String.format("Task deleted successfully (ID: %d)", id));
    } else {
      System.out.println(String.format("Couldn't delete task (ID: %d)", id));
    }
  }

  public void updateTask(int id, String description) {
    Task task = findById(id);
    if (task != null) {
      task.description = description;
      saveTasks();
      System.out.println(String.format("Task updated successfully (ID: %d)", id));
    } else {
      System.out.println(String.format("Couldn't find the task to update (ID: %d)", id));
    }
  }

  public void updateStatus(int id, int status) {
    Task task = findById(id);
    if (task != null) {
      task.status = status;
      saveTasks();
      System.out.println(String.format("Task status changed successfully (ID: %d)", id));
    } else {
      System.out.println(String.format("Couldn't find the task to update (ID: %d)", id));
    }
  }

  public List<Task> listByStatus(Integer status) {
    if (status == null) {
      return tasks;
    } else {
      return tasks.stream().filter(task -> task.status == status).collect(Collectors.toList());
    }
  }
}
