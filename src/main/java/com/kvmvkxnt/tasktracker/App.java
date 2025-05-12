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
import picocli.CommandLine.Spec;

@Command(
    name = "task-cli",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "Task tracker CLI application",
    subcommands = {
      AddCommand.class,
      DeleteCommand.class,
      ListCommand.class,
      MarkDoneCommand.class,
      MarkInProgressCommand.class,
      MarkTodoCommand.class,
      UpdateCommand.class
    })
public class App implements Runnable {
  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Spec CommandLine.Model.CommandSpec spec;

  @Override
  public void run() {
    spec.commandLine().getOut().println("Use `--help` to see available commands.");
  }
}
