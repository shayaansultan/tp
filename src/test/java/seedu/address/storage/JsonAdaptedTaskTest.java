package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class JsonAdaptedTaskTest {
    @Test
    public void toModelType_validTaskString_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask("[_] Buy milk");
        assertEquals(new Task(new Description("Buy milk")), task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskString_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("[_]Buy milk");
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_constructorTask_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(new Task(new Description("Buy milk")));
        assertEquals(new Task(new Description("Buy milk")), task.toModelType());
    }
}
