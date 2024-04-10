package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.util.CommandUtil.findEmployeeByUniqueId;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static final String MESSAGE_SUCCESS = "Task unmarked %1$s";
    private static final Logger LOGGER = Logger.getLogger(UnmarkTaskCommand.class.getName());

    private final UniqueId uid;
    private final int taskNumber;

    /**
     * Constructor for UnmarkTaskCommand
     * 
     * @param uid        UniqueId of the employee
     * @param taskNumber Task number to be marked as done
     */
    public UnmarkTaskCommand(UniqueId uid, int taskNumber) {
        requireNonNull(uid);
        if (taskNumber <= 0) {
            throw new IllegalArgumentException("Task number must be greater than zero.");
        }
        this.uid = uid;
        this.taskNumber = taskNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Employee employee = findEmployeeByUniqueId(model, uid);

        try {
            employee.unmarkTask(taskNumber - 1);
            model.updateFilteredEmployeeList(Model.PREDICATE_SHOW_ALL_EMPLOYEES);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "Task number out of bounds", e);
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, employee.getTask(taskNumber - 1).toString()));
    }

    /**
     * Getter for UniqueId
     * 
     * @return UniqueId of the employee
     */
    public UniqueId getUid() {
        return this.uid;
    }

    /**
     * Getter for task number
     * 
     * @return Task number to be marked as not done
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }
}
