package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void isSameTask() {
        Task task1 = new Task(new Description("Buy milk"));
        Task task2 = new Task(new Description("Buy milk"));
        Task task3 = new Task(new Description("Buy bread"));

        // same object -> returns true
        assertTrue(task1.isSameTask(task1));

        // same values -> returns true
        assertTrue(task1.isSameTask(task2));

        // different values -> returns false
        assertFalse(task1.isSameTask(task3));
    }

    @Test
    public void testToString() {
        Task task = new Task(new Description("Buy milk"));
        assertEquals("[_] Buy milk", task.toString());

        task.mark();
        assertEquals("[X] Buy milk", task.toString());
    }

    @Test
    public void testIsValidTaskString() {
        assertTrue(Task.isValidTaskString("[_] Buy milk"));
        assertTrue(Task.isValidTaskString("[X] Buy milk"));
        assertFalse(Task.isValidTaskString("[_]Buy milk"));
        assertFalse(Task.isValidTaskString("[X]Buy milk"));
        assertFalse(Task.isValidTaskString("[ ] Buy milk"));
        assertFalse(Task.isValidTaskString("Buy milk"));
    }

    @Test
    public void testParseTaskString() {
        Task task = Task.parseTaskString("[_] Buy milk");
        assertEquals(new Task(new Description("Buy milk")), task);

        task = Task.parseTaskString("[X] Buy milk");
        Task doneTask = new Task(new Description("Buy milk"));
        doneTask.mark();
        assertEquals(doneTask, task);
    }
}
