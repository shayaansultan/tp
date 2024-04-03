package seedu.address.model.tasklist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

/**
 * Represents a TodoList in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class TaskList {

    // Data fields
    private final ObservableList<Task> tasks;

    /**
     * Every field must be present and not null.
     */
    public TaskList() {
        this.tasks = FXCollections.observableArrayList();
    }

    public ObservableList<Task> getTasks() {
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
     * Marks a task as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Returns the number of tasks in the todo list.
     *
     * @return the number of tasks in the todo list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns completed tasks in the todo list.
     *
     * @return the number of completed tasks in the todo list
     */
    public int getCompletedTasks() {
        return (int) tasks.stream().filter(Task::isDone).count();
    }

    /**
     * Returns pending tasks in the todo list.
     *
     * @return the number of pending tasks in the todo list
     */
    public int getPendingTasks() {
        return size() - getCompletedTasks();
    }

    /**
     * Retuns the completion rate of the todo list.
     *
     * @return the completion rate of the todo list
     */
    public double getCompletionRate() {
        if (size() == 0) {
            return 0;
        }
        return (double) (getCompletedTasks() / size()) * 100;
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
