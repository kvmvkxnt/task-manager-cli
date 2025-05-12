package com.kvmvkxnt.tasktracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

  public int id;
  public String description;
  public int status;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Task() {}

  public Task(
      int id, String description, int status, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    String statusString;
    switch (status) {
      case 1:
        statusString = "IN_PROGRESS";
        break;
      case 2:
        statusString = "DONE";
        break;
      case 0:
        statusString = "TODO";
        break;
      default:
        statusString = "UNKNOWN";
        break;
    }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return String.format(
        "| %d | %s | %s | %s | %s |",
        id,
        description,
        statusString,
        createdAt.format(dateTimeFormatter),
        updatedAt.format(dateTimeFormatter));
  }
}
