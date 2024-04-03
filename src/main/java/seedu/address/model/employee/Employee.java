package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.tasklist.TaskList;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Employee {

    // Identity fields
    private final Name name;
    private final Team team;
    private final Role role;

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final UniqueId uid;
    private final TaskList taskList;

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, Address address, Team team, Role role, Set<Tag> tags,
            UniqueId uid, List<Task> tasks) {
        requireAllNonNull(name, phone, email, address, team, role, tags, uid);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.team = team;
        this.role = role;
        this.tags.addAll(tags);
        this.uid = uid;
        this.taskList = new TaskList();
        for (Task task : tasks) {
            this.taskList.addTask(task);
        }
    }

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, Address address, Team team, Role role, Set<Tag> tags,
            UniqueId uid) {
        requireAllNonNull(name, phone, email, address, team, role, tags, uid);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.team = team;
        this.role = role;
        this.tags.addAll(tags);
        this.uid = uid;
        this.taskList = new TaskList();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Team getTeam() {
        return team;
    }

    public Role getRole() {
        return role;
    }

    public UniqueId getUid() {
        return uid;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ObservableList<Task> getTasks() {
        return taskList.getTasks();
    }

    /**
     * Adds a task to the todo list.
     */
    public void addTask(Description description) {
        taskList.addTask(new Task(description));
    }

    /**
     * Returns true if both employees have the same name and id
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }
        return otherEmployee != null && otherEmployee.getName().equals(getName())
                && this.uid.equals(otherEmployee.getUid());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return name.equals(otherEmployee.name)
                && phone.equals(otherEmployee.phone)
                && email.equals(otherEmployee.email)
                && address.equals(otherEmployee.address)
                && team.equals(otherEmployee.team)
                && role.equals(otherEmployee.role)
                && tags.equals(otherEmployee.tags)
                && uid.equals(otherEmployee.uid);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, team, role, tags, uid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("team", team)
                .add("role", role)
                .add("tags", tags)
                .add("uid", uid)
                .toString();
    }

    /**
     * Removes a task from the todo list.
     * 
     * @param taskNumber the index of the task to be removed
     */
    public void unmarkTask(int taskNumber) {
        taskList.unmarkTask(taskNumber);
    }

    /**
     * Returns the task object at the specified index.
     * 
     * @param taskNumber the index of the task to be returned
     * 
     * @return the number of tasks in the todo list
     */
    public Task getTask(int taskNumber) {
        return taskList.getTasks().get(taskNumber);
    }

    /**
     * Removes a task from the todo list.
     * 
     * @param taskNumber the index of the task to be removed
     */
    public void markTask(int taskNumber) {
        taskList.markTask(taskNumber);
    }

    /**
     * Removes all tasks from the todo list.
     */
    public void clearTasks() {
        taskList.getTasks().clear();
    }

    /**
     * Returns the completion rate of tasks for this employee.
     *
     * @return the completion rate of tasks
     */
    public double getTaskCompletionRate() {
        return taskList.getCompletionRate();
    }
}
