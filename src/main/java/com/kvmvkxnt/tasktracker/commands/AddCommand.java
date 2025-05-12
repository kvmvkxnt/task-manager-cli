package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "add", description = "Add a new task")
public class AddCommand implements Runnable {
  @Parameters(index = "0", description = "The description of the task")
  private String description;

  private final TaskManager taskManager = new TaskManager();

  @Override
  public void run() {
    taskManager.addTask(description);
  }
}
