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
        TaskList todoList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        todoList.addTask(task);

        // check if task is added
        assertEquals(todoList.getTasks().get(0), task);
    }

    @Test
    public void markTask() {
        TaskList todoList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        todoList.addTask(task);
        todoList.markTask(0);

        // check if task is marked
        assertTrue(todoList.getTasks().get(0).isDone());
    }

    @Test
    public void unmarkTask() {
        TaskList todoList = new TaskList();
        Task task = new Task(new Description("Buy milk"));
        todoList.addTask(task);
        todoList.markTask(0);
        todoList.unmarkTask(0);

        // check if task is unmarked
        assertFalse(todoList.getTasks().get(0).isDone());
    }
}
