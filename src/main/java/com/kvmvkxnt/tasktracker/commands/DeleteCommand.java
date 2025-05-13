package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "delete", description = "Delete a task")
public class DeleteCommand implements Runnable {
  private int id;

  @Parameters(index = "0", description = "Id of the task to delete")
  public void setId(int id) {
    this.id = id;
  }

  private final TaskManager taskManager;

  public DeleteCommand(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

  @Override
  public void run() {
    boolean removed = taskManager.deleteTask(id);
    if (removed) {
      System.out.println("Task deleted successfully!");
    } else {
      System.out.println("Couldn't delete task! No task with such id.");
    }
  }
}
