package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}