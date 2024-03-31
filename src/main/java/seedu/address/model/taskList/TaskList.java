package seedu.address.model.taskList;

import seedu.address.model.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TodoList in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class TaskList {

    // Data fields
    private final List<Task> tasks;

    /**
     * Every field must be present and not null.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the todo list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the todo list.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks a task as done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

}