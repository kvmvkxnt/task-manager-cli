package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "update", description = "Update task's description")
public class UpdateCommand implements Runnable {
  @Parameters(index = "0", description = "Id of the task to update")
  private int id;

  @Parameters(index = "1", description = "New description of the task")
  private String description;

  private final TaskManager taskManager = new TaskManager();

  @Spec CommandSpec spec;

  @Override
  public void run() {
    boolean updated = taskManager.updateTask(id, description);
    if (updated) {
      spec.commandLine().getOut().println("Task updated successfully!");
    } else {
      spec.commandLine().getOut().println("Couldn't find the task to update!");
    }
  }
}
