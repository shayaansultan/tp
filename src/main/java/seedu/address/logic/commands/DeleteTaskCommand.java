package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;

/**
 * Deletes a task from the todo list of an employee.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deleteTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the todo list of an employee. "
            + "Parameters: UID (must be a positive integer) TASK_INDEX (must be a positive integer)\n"
            + "The TASK_INDEX is one based and not zero based.\n"
            + "Example: " + COMMAND_WORD + " uid/1 1";

    public static final String MESSAGE_SUCCESS = "Task deleted successfully!";

    private final UniqueId uid;
    private final int taskIndex;

    /**
     * Creates a DeleteTaskCommand to delete a task from the todo list of an
     * employee.
     */
    public DeleteTaskCommand(UniqueId uid, int taskIndex) {
        assert uid != null;
        assert taskIndex > 0;
        this.uid = uid;
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes a task from the todo list of an employee.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult representing the result of the command execution.
     * @throws CommandException if the employee with the given UID does not exist in
     *                         the model or the task index is invalid.
     */
    private CommandResult deleteTask(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employee = null;

        for (Employee e : lastShownList) {
            if ((e.getUid().getUidValue()).equals(uid.getUidValue())) {
                employee = e;
                break;
            }
        }

        if (employee == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
        }

        if (employee.getTasks().isEmpty()) {
            throw new CommandException("The employee has no tasks to delete.");
        }

        if (taskIndex > employee.getTasks().size() || taskIndex <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        try {
            employee.deleteTask(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return deleteTask(model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteTaskCommand
                        && uid.equals(((DeleteTaskCommand) other).uid)
                        && taskIndex == ((DeleteTaskCommand) other).taskIndex);
    }

    @Override
    public String toString() {
        return "Delete task for employee with UID: " + uid + " at index: " + taskIndex;
    }
}
