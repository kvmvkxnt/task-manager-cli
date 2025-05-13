package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.Task;
import com.kvmvkxnt.tasktracker.TaskManager;
import java.util.List;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "list", description = "List tasks")
public class ListCommand implements Runnable {
  private String statusFilter;

  @Parameters(
      index = "0",
      description = "Status of the task: todo, done, in-progress",
      arity = "0..1")
  public void setStatusFilter(String statusFilter) {
    this.statusFilter = statusFilter;
  }

  private final TaskManager taskManager;

  public ListCommand(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

  @Override
  public void run() {
    Integer status = null;
    if (statusFilter != null) {
      switch (statusFilter) {
        case "todo":
          status = 0;
          break;
        case "done":
          status = 2;
          break;
        case "in-progress":
          status = 1;
          break;
        default:
          System.out.println("Invalid status.");
          return;
      }
    }

    List<Task> tasks;
    if (status == null) {
      tasks = taskManager.getAllTasks();
    } else {
      tasks = taskManager.listByStatus(status);
    }

    if (tasks.size() == 0) {
      System.out.println("No tasks found");
    } else {
      System.out.println(
          "-------------------------------------------------------\n"
              + "| ID | Description | Status | Created at | Updated at |\n"
              + "-------------------------------------------------------");
      tasks.forEach(task -> System.out.println(task.toString()));
    }
  }
}
