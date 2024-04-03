package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;

/**
 * Marks a task as not done for an employee.
 */
public class UnmarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "Task unmarked as not done: %1$s";

    private final UniqueId uid;
    private final int taskNumber;

    /**
     * Constructor for UnmarkTaskCommand
     * @param uid UniqueId of the employee
     * @param taskNumber Task number to be marked as done
     */
    public UnmarkTaskCommand(UniqueId uid, int taskNumber) {
        this.uid = uid;
        this.taskNumber = taskNumber - 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employee = null;

        for (Employee e : lastShownList) {
            if (e.getUid().equals(this.uid)) {
                employee = e;
                break;
            }
        }

        if (employee == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
        }

        try {
            employee.unmarkTask(this.taskNumber);
            model.updateFilteredEmployeeList(Model.PREDICATE_SHOW_ALL_EMPLOYEES);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, employee.getTask(this.taskNumber)));
    }

    /**
     * Getter for UniqueId
     * @return UniqueId of the employee
     */
    public UniqueId getUid() {
        return this.uid;
    }

    /**
     * Getter for task number
     * @return Task number to be marked as not done
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }
}
