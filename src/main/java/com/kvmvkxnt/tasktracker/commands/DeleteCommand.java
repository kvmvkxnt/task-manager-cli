package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "delete", description = "Delete a task")
public class DeleteCommand implements Runnable {
  @Parameters(index = "0", description = "Id of the task to delete")
  private int id;

  private final TaskManager taskManager = new TaskManager();

  @Spec CommandSpec spec;

  @Override
  public void run() {
    boolean removed = taskManager.deleteTask(id);
    if (removed) {
      spec.commandLine().getOut().println("Task deleted successfully!");
    } else {
      spec.commandLine().getOut().println("Couldn't delete task! No task with such id.");
    }
  }
}
