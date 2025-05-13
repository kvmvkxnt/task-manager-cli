package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "mark-in-progress", description = "Mark the specified task as in-progress")
public class MarkInProgressCommand implements Runnable {
  @Parameters(index = "0", description = "Id of the task to mark")
  private int id;

  private final TaskManager taskManager = new TaskManager();

  @Spec CommandSpec spec;

  @Override
  public void run() {
    boolean marked = taskManager.updateStatus(id, 1);
    if (marked) {
      spec.commandLine().getOut().println("Task status changed successfully!");
    } else {
      spec.commandLine().getOut().println("Couldn't find the task to update!");
    }
  }
}
