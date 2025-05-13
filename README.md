
# Task Tracker CLI

A simple task tracker made with picocli and maven, fully written in Java. It can be useful for people who doesn't like these new GUI-ed apps and just wants to track his tasks in his lovely terminal.



## Additional information

All tasks are normally saved to ```$HOME/.local/share/task-cli/tasks.json```. App has no option to edit this path, unless you change the code. Binary in release was built and tested only on ***macos arm64***, so if it doesn't work on your pc, you will have to manually build the project.


## Tech Stack

**Help:** Picocli, Jackson

**Version:** Java 21.0.7

**Project Management:** Maven

**Image Creation:** GraalVM

## Build

To build the project locally, you will need java21.0.7-graal selected and maven installed. You can install maven by downloading your favourite package manager like apt, brew, scoop or pacman. To install needed java version, refer to GraalVM official website: https://www.graalvm.org

Check java version first with ```java -version```. It must output something like this:

```bash
  java version "21.0.7" 2025-04-15 LTS
  Java(TM) SE Runtime Environment Oracle GraalVM 21.0.7+8.1 (build 21.0.7+8-LTS-jvmci-23.1-b60)
  Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 21.0.7+8.1 (build 21.0.7+8-LTS-jvmci-23.1-b60, mixed mode, sharing)
```

Now, clone the project:

```bash
  git clone https://github.com/kvmvkxnt/task-manager-cli.git
```

Enter the project directory:

```bash
  cd task-manager-cli
```

Build the jar file by running following command:
```bash
  mvn clean package
```
If you want to skip tests, run:
```bash
  mvn package -Dmaven.test.skip
```
If you want to only run tests, you can run
```bash
  mvn test
```
You can either use a jar file, or if you want to build a binary, follow this step. After building the jar file, build a binary with native image:
```bash
  native-image -H:ConfigurationFileDirectories=src/main/resources/META-INF/native-image -jar target/task-tracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar task-cli
```
And now, you're good to go. Binary should be located in the root directory of the project.
## Usage/Examples

You can either build the jar and use jar, or you can build a binary. If you decided to use jar, you need to use ```java -jar target/task-tracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar``` and commands. If you built a binary, you can go just with ```./task-cli```.

To show a help message, run:

```bash
  task-cli --help
```

Listing available tasks can done by:

```bash
  task-cli list
```

If you want to see specific tasks, you can filter them by their status:

```bash
  task-cli list (done | todo | in-progress)
```

To add a task, run:
```bash
  task-cli add "Your task"
```

To delete a task, run:
```bash
  task-cli delete (id of the task)
  task-cli delete 0
```

To mark your task with different status, you can use following commands:
```bash
  task-cli mark-todo (id of the task)
  task-cli mark-in-progress 0
  task-cli mark-done 3
```

To update your task with different description, use:
```bash
  task-cli update (id of the task) "Your new description"
```
