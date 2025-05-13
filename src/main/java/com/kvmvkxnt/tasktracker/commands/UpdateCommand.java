package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "update", description = "Update task's description")
public class UpdateCommand implements Runnable {
  private int id;

  @Parameters(index = "0", description = "Id of the task to update")
  public void setId(int id) {
    this.id = id;
  }

  private String description;

  @Parameters(index = "1", description = "New description of the task")
  public void setDescription(String description) {
    this.description = description;
  }

  private final TaskManager taskManager;

  public UpdateCommand(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

  @Override
  public void run() {
    boolean updated = taskManager.updateTask(id, description);
    if (updated) {
      System.out.println("Task updated successfully!");
    } else {
      System.out.println("Couldn't find the task to update!");
    }
  }
}
