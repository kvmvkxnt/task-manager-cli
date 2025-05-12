package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "mark-in-progress", description = "Mark the specified task as in-progress")
public class MarkInProgressCommand implements Runnable {
  @Parameters(index = "0", description = "Id of the task to mark")
  private int id;

  private final TaskManager taskManager = new TaskManager();

  @Override
  public void run() {
    taskManager.updateStatus(id, 1);
  }
}
