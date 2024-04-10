package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
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
    public static final String MESSAGE_SUCCESS = "Task unmarked as not done: %1$s";
    private static final Logger LOGGER = Logger.getLogger(UnmarkTaskCommand.class.getName());

    private final UniqueId uid;
    private final int taskNumber;

    /**
     * Constructor for UnmarkTaskCommand
     * @param uid UniqueId of the employee
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
     * Finds an employee in the model's employee list by their unique identifier (UID).
     * This method searches through the filtered list of employees in the model and
     * returns the first employee that matches the given UID. If no such employee is found,
     * a CommandException is thrown.
     *
     * @param model The model containing the list of employees to search.
     * @param uid The unique identifier of the employee to find.
     * @return The employee with the specified UID.
     * @throws CommandException If no employee with the specified UID can be found in the model's list.
     */
    private Employee findEmployeeByUniqueId(Model model, UniqueId uid) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Optional<Employee> employee = lastShownList.stream()
                .filter(e -> e.getUid().equals(uid))
                .findFirst();

        if (employee.isEmpty()) {
            LOGGER.log(Level.WARNING, "Invalid employee UID: " + uid);
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
        }

        return employee.get();
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
