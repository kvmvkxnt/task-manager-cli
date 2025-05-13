package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "mark-in-progress", description = "Mark the specified task as in-progress")
public class MarkInProgressCommand implements Runnable {
  private int id;

  @Parameters(index = "0", description = "Id of the task to mark")
  public void setId(int id) {
    this.id = id;
  }

  private final TaskManager taskManager;

  public MarkInProgressCommand(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

  @Override
  public void run() {
    boolean marked = taskManager.updateStatus(id, 1);
    if (marked) {
      System.out.println("Task status changed successfully!");
    } else {
      System.out.println("Couldn't find the task to update!");
    }
  }
}
