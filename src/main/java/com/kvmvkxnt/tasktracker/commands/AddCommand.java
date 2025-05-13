package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.Task;
import com.kvmvkxnt.tasktracker.TaskManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "add", description = "Add a new task")
public class AddCommand implements Runnable {
  @Parameters(index = "0", description = "The description of the task")
  private String description;

  private final TaskManager taskManager = new TaskManager();

  @Spec CommandSpec spec;

  @Override
  public void run() {
    Task task = taskManager.addTask(description);
    spec.commandLine().getOut().println(String.format("Task added successfully (ID: %d)", task.id));
  }
}
