package com.kvmvkxnt.tasktracker;

import com.kvmvkxnt.tasktracker.commands.AddCommand;
import com.kvmvkxnt.tasktracker.commands.DeleteCommand;
import com.kvmvkxnt.tasktracker.commands.ListCommand;
import com.kvmvkxnt.tasktracker.commands.MarkDoneCommand;
import com.kvmvkxnt.tasktracker.commands.MarkInProgressCommand;
import com.kvmvkxnt.tasktracker.commands.MarkTodoCommand;
import com.kvmvkxnt.tasktracker.commands.UpdateCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "task-cli",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "Task tracker CLI application")
public class App implements Runnable {
  public static void main(String[] args) {
    TaskManager taskManager = new TaskManager();

    CommandLine cmd = new CommandLine(new App());
    cmd.addSubcommand("add", new AddCommand(taskManager));
    cmd.addSubcommand("delete", new DeleteCommand(taskManager));
    cmd.addSubcommand("list", new ListCommand(taskManager));
    cmd.addSubcommand("mark-done", new MarkDoneCommand(taskManager));
    cmd.addSubcommand("mark-in-progress", new MarkInProgressCommand(taskManager));
    cmd.addSubcommand("mark-todo", new MarkTodoCommand(taskManager));
    cmd.addSubcommand("update", new UpdateCommand(taskManager));

    int exitCode = cmd.execute(args);
    System.exit(exitCode);
  }

  @Override
  public void run() {
    System.out.println("Use `--help` to see available commands.");
  }
}
