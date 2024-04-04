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
    public void test_ToString() {
        TaskList taskList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        taskList.addTask(task);

        // check if toString is correct
        assertEquals("[_] Buy milk\n", taskList.toString());
    }

    @Test
    public void test_size() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());

        taskList.addTask(new Task(new Description("Task 1")));
        assertEquals(1, taskList.size());

        taskList.addTask(new Task(new Description("Task 2")));
        assertEquals(2, taskList.size());
    }

    @Test
    public void test_getCompletedTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task(new Description("Task 1")));
        taskList.addTask(new Task(new Description("Task 2")));

        assertEquals(0, taskList.getCompletedTasks());

        taskList.markTask(0);
        assertEquals(1, taskList.getCompletedTasks());

        taskList.markTask(1);
        assertEquals(2, taskList.getCompletedTasks());
    }

    @Test
    public void test_getPendingTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task(new Description("Task 1")));
        taskList.addTask(new Task(new Description("Task 2")));

        assertEquals(2, taskList.getPendingTasks());

        taskList.markTask(0);
        assertEquals(1, taskList.getPendingTasks());

        taskList.markTask(1);
        assertEquals(0, taskList.getPendingTasks());
    }

    @Test
    public void test_getCompletionRate() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getCompletionRate());

        taskList.addTask(new Task(new Description("Task 1")));
        taskList.addTask(new Task(new Description("Task 2")));
        assertEquals(0, taskList.getCompletionRate());

        taskList.markTask(0);
        assertEquals(50, taskList.getCompletionRate());

        taskList.markTask(1);
        assertEquals(100, taskList.getCompletionRate());
    }

}
