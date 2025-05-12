package com.kvmvkxnt.tasktracker.commands;

import com.kvmvkxnt.tasktracker.Task;
import com.kvmvkxnt.tasktracker.TaskManager;
import java.util.List;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "list", description = "List tasks")
public class ListCommand implements Runnable {
  @Parameters(
      index = "0",
      description = "Status of the task: todo, done, in-progress",
      arity = "0..1")
  private String statusFilter;

  private final TaskManager taskManager = new TaskManager();

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
      System.out.println("-------------------------------------------------------");
      System.out.println("| ID | Description | Status | Created at | Updated at |");
      System.out.println("-------------------------------------------------------");
      tasks.forEach(task -> System.out.println(task.toString()));
    }
  }
}
