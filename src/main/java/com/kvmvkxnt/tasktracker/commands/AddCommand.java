package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.Task;
import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "add", description = "Add a new task")
public class AddCommand implements Runnable {
  private String description;

  @Parameters(index = "0", description = "The description of the task")
  public void setDescription(String description) {
    this.description = description;
  }

  private final TaskManager taskManager;

  public AddCommand(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

  @Override
  public void run() {
    Task task = taskManager.addTask(description);
    System.out.println(String.format("Task added successfully (ID: %d)", task.id));
  }
}
