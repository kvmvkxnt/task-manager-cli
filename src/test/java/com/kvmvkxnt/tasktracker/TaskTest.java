package com.kvmvkxnt.tasktracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class TaskTest {
  @Test
  public void testToStringTypedFormat() {
    LocalDateTime statusTimestamp = LocalDateTime.now();
    Task task = new Task(1, "New task", 0, statusTimestamp, statusTimestamp);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDateTime = statusTimestamp.format(dateTimeFormatter);

    assertEquals(
        String.format("| 1 | New task | TODO | %s | %s |", formattedDateTime, formattedDateTime),
        task.toString(),
        "Task toString method should show proper test with proper status (TODO)");
  }

  @Test
  public void testToStringUnknownFormat() {
    LocalDateTime statusTimestamp = LocalDateTime.now();
    Task task = new Task(1, "New task", 321, statusTimestamp, statusTimestamp);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDateTime = statusTimestamp.format(dateTimeFormatter);

    assertEquals(
        String.format("| 1 | New task | UNKNOWN | %s | %s |", formattedDateTime, formattedDateTime),
        task.toString(),
        "Task status must be UNKNOWN due to 321 status");
  }
}
