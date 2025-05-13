package com.kvmvkxnt.tasktracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {

  private File tempFile;
  private TaskManager taskManager;

  @BeforeEach
  void setUp() throws Exception {
    tempFile = Files.createTempFile("task-manager-test", ".json").toFile();
    taskManager = new TaskManager(tempFile);
  }

  @AfterEach
  void tearDown() throws IOException {
    tempFile.delete();
  }

  @Test
  public void testAddTask() {
    String taskDescription = "Test task";

    taskManager.addTask(taskDescription);

    assertEquals(1, taskManager.getAllTasks().size(), "Task list size should be 1");

    Task addedTask = taskManager.getAllTasks().get(0);
    assertEquals(
        taskDescription,
        addedTask.description,
        String.format("Task's description should be: %s", taskDescription));
  }

  @Test
  public void testDeleteTask() {
    String testDescription = "Task to delete";

    taskManager.addTask(testDescription);

    boolean removed = taskManager.deleteTask(1);

    assertEquals(0, taskManager.getAllTasks().size(), "Task list size should be 0");
    assertTrue(removed, "Deletion method should return true");
  }

  @Test
  public void testDeleteIncorrectTask() {
    boolean removed = taskManager.deleteTask(1);

    assertTrue(!removed, "Deletion method should return false");
  }

  @Test
  public void testUpdateTask() {
    String taskInitialDescription = "Test task to update";
    String taskUpdatedDescription = "Updated task";

    LocalDateTime initialTask = taskManager.addTask(taskInitialDescription).updatedAt;

    boolean updated = taskManager.updateTask(1, taskUpdatedDescription);

    assertTrue(updated, "Task update method should return true");
    assertEquals(
        taskUpdatedDescription,
        taskManager.getAllTasks().get(0).description,
        "Description of the task should be updated");
    assertNotEquals(
        initialTask,
        taskManager.getAllTasks().get(0).updatedAt,
        "Task updated at field should be changed");
  }

  @Test
  public void testUpdateNonexistentTask() {
    boolean updated = taskManager.updateTask(1, "New description");

    assertTrue(!updated, "Update method should return false (for tests it is true)");
  }

  @Test
  public void testUpdateStatus() {
    String taskDescription = "Test task for status update";

    Task task = taskManager.addTask(taskDescription);
    int initialStatus = task.status;
    LocalDateTime initialUpdatedAt = task.updatedAt;

    boolean updated = taskManager.updateStatus(1, 2);

    assertNotEquals(
        initialStatus, taskManager.getAllTasks().get(0).status, "Task status should be different");
    assertTrue(updated, "Task status updated method should return true");
    assertNotEquals(
        initialUpdatedAt,
        taskManager.getAllTasks().get(0).updatedAt,
        "Task updated at should be different");
  }

  @Test 
  public void testUpdatedStatusNonexistent() {
    boolean updated = taskManager.updateStatus(1, 2);

    assertTrue(!updated, "Update status method should return false (for tests it is true)");
  }
}
