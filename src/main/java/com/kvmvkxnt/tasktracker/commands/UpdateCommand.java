package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "update", description = "Update task's description")
public class UpdateCommand implements Runnable {
  @Parameters(index = "0", description = "Id of the task to update")
  private int id;

  @Parameters(index = "1", description = "New description of the task")
  private String description;

  private final TaskManager taskManager = new TaskManager();

  @Override
  public void run() {
    taskManager.updateTask(id, description);
  }
}
