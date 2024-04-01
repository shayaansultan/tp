package seedu.address.model.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class TaskListTest {
    @Test
    public void addTask() {
        TaskList taskList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        taskList.addTask(task);

        // check if task is added
        assertEquals(taskList.getTasks().get(0), task);
    }

    @Test
    public void removeTask() {
        TaskList taskList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        taskList.addTask(task);
        taskList.removeTask(0);

        // check if task is removed
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    public void markTask() {
        TaskList taskList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        taskList.addTask(task);
        taskList.markTask(0);

        // check if task is marked
        assertTrue(taskList.getTasks().get(0).isDone());
    }

    @Test
    public void unmarkTask() {
        TaskList taskList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        taskList.addTask(task);
        taskList.markTask(0);
        taskList.unmarkTask(0);

        // check if task is unmarked
        assertFalse(taskList.getTasks().get(0).isDone());
    }

    @Test
    public void testToString() {
        TaskList taskList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        taskList.addTask(task);

        // check if toString is correct
        assertEquals("[_] Buy milk\n", taskList.toString());
    }
}
