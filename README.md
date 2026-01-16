# Task Tracker CLI

A simple command-line interface (CLI) application for tracking tasks, written in Java. This project demonstrates a pure Java implementation without using any external libraries or frameworks, managing data persistence via a JSON file.

## Features

- **Add Task**: Create a new task with a description.
- **Update Task**: Modify the description of an existing task.
- **Delete Task**: Remove a task by its ID.
- **Mark Status**: Change the status of a task to `TODO`, `IN_PROGRESS`, or `DONE`.
- **List Tasks**:
    - List all tasks.
    - List tasks by status (todo, done, in_progress).
- **Data Persistence**: Tasks are saved automatically to a JSON file (`data/tasks.json`).

## Prerequisites

- Java Development Kit (JDK) 21 or higher.

## How to Build and Run

1.  **Compile the source code:**
    Open a terminal in the project root directory and run:
    ```bash
    javac -d out src/com/tasktracker/Main.java src/com/tasktracker/cli/*.java src/com/tasktracker/exception/*.java src/com/tasktracker/model/*.java src/com/tasktracker/repository/*.java src/com/tasktracker/service/*.java src/com/tasktracker/util/*.java
    ```

2.  **Run the application:**
    Use the `java` command to execute the compiled classes. You need to pass the arguments as if you were typing in the CLI.

    *Example: Adding a task*
    ```bash
    java -cp out com.tasktracker.Main task-cli add "Buy groceries"
    ```

    *Example: Listing tasks*
    ```bash
    java -cp out com.tasktracker.Main task-cli list
    ```

## Usage Examples

The application expects the first argument to be `task-cli`, followed by the command and arguments.

*   **Add a new task:**
    ```bash
    task-cli add "Finish the project"
    ```
*   **List all tasks:**
    ```bash
    task-cli list
    ```
*   **List tasks by status:**
    ```bash
    task-cli list done
    ```
*   **Update a task (ID 1):**
    ```bash
    task-cli update 1 "Finish the README"
    ```
*   **Mark a task as in progress (ID 1):**
    ```bash
    task-cli mark 1 in_progress
    ```
*   **Delete a task (ID 1):**
    ```bash
    task-cli delete 1
    ```

## Project Structure

The project follows a layered architecture:

*   `src/com/tasktracker/`
    *   `Main.java`: Entry point of the application.
    *   `cli/`: Handles command parsing and user interaction.
    *   `service/`: Contains business logic.
    *   `repository/`: Handles data persistence (file I/O).
    *   `model/`: Data classes (`Task`, `Status`).
    *   `util/`: Utility classes (e.g., JSON handling).
