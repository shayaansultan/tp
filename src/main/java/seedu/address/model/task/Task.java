package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Task {

    // Identity fields
    private final Description description;

    // Data fields
    private boolean isDone;

    /**
     * Every field must be present and not null.
     */
    public Task(Description description) {
        requireAllNonNull(description);
        this.description = description;
        this.isDone = false;
    }

    public Description getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.isDone() == isDone();
    }

    @Override
    public int hashCode() {
        return description.hashCode() + (isDone ? 1 : 0);
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
