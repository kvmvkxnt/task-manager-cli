package com.kvmvkxnt.tasktracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class AppTest {
  App app = new App();

  @Test
  void testRunWithoutArgumentsShowsHelpHint() {
    CommandLine cmd = new CommandLine(app);

    // Capture output
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(outContent, true);
    cmd.setOut(writer);

    int exitCode = cmd.execute(); // no arguments
    String output = outContent.toString().trim();

    assertEquals(0, exitCode, "Application exit code should be 0");
    assertTrue(
        output.contains("Use `--help` to see available commands."),
        "Application must show tip when executed without commands");
  }

  @Test
  void testHelpOptionOutputsUsage() {
    CommandLine cmd = new CommandLine(app);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(outContent, true);
    cmd.setOut(writer);

    int exitCode = cmd.execute("--help");
    String output = outContent.toString().trim();

    assertEquals(0, exitCode, "Application exit code should be 0");
    assertTrue(output.contains("Usage: task-cli"), "Application must show help message.");
  }

  @Test
  void testUnknownCommandFails() {
    CommandLine cmd = new CommandLine(new App());

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(outContent, true);
    cmd.setOut(writer);
    cmd.setErr(writer);

    int exitCode = cmd.execute("nonexistent");
    String output = outContent.toString().trim();

    assertNotEquals(0, exitCode, "Application exit code should not be 0");
    assertTrue(
        output.contains("Unmatched argument at index 0: 'nonexistent'"),
        "Application must show error message");
  }
}
