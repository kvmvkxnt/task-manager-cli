package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "delete", description = "Delete a task")
public class DeleteCommand implements Runnable {
  @Parameters(index = "0", description = "Id of the task to delete")
  private int id;

  private final TaskManager taskManager = new TaskManager();

  @Override
  public void run() {
      taskManager.deleteTask(id);
  }
}
