package com.kvmvkxnt.tasktracker;

public class Task {

  public int id;
  public String description;
  public int status;

  public Task() {}

  public Task(int id, String description, int status) {
    this.id = id;
    this.description = description;
    this.status = status;
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
      default:
        statusString = "TODO";
        break;
    }
    return String.format("[%d] %s (%s)", id, description, statusString);
  }
}
