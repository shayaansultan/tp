package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskString;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(String taskString) {
        this.taskString = taskString;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskString = source.toString();
    }

    @JsonValue
    public String getTaskString() {
        return taskString;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's
     * {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskString == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        if (!Task.isValidTaskString(taskString)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        return Task.parseTaskString(taskString);
    }

}
