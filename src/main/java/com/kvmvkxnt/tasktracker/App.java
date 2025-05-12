package com.kvmvkxnt.tasktracker;

import com.kvmvkxnt.tasktracker.commands.AddCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "task-cli",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "Task tracker CLI application",
    subcommands = {AddCommand.class})
public class App implements Runnable {
  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public void run() {
    System.out.println("Use `--help` to see available commands.");
  }
}
