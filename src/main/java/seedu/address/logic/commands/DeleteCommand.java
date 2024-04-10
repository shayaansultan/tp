package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;

/**
 * Deletes an employee identified using it's displayed index from the address
 * book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by the index number used in the displayed employee list, "
            + "by the name, or by the unique identifier (UID).\n"
            + "Parameters: INDEX (must be a positive integer), NAME (name of the employee), "
            + "or uid/UID (unique identifier of the employee)\n"
            + "Examples:\n"
            + COMMAND_WORD + " 1\n" // example of deletion by index
            + COMMAND_WORD + " John Doe\n" // example of deletion by name
            + COMMAND_WORD + " uid/100"; // example of deletion by UID

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted Employee: %1$s";
    private static final String MESSAGE_MULTIPLE_EMPLOYEES_FOUND = "Multiple employees "
            + "with this name found. Please delete by UID.\n"
            + "Format: delete uid/UID_NUMBER\n" + "Example: delete uid/101";
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class.getName());
    private final Index targetIndex;
    private final String targetName;
    private final UniqueId uid;

    /**
     * Null constructor for DeleteCommand
     */
    public DeleteCommand() {
        this.targetIndex = null;
        this.targetName = null;
        this.uid = null;
    }

    /**
     * Constructor for index-based deletion
     *
     * @param targetIndex index of the employee in the filtered employee list to
     *                    delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetName = null; // Name is not used in this context
        this.uid = null; // UniqueId is not used in this context
    }

    /**
     * Constructor for name-based deletion
     *
     * @param targetName name of the employee to delete
     */
    public DeleteCommand(String targetName) {
        this.targetIndex = null; // Index is not used in this context
        this.targetName = targetName;
        this.uid = null; // UniqueId is not used in this context
    }

    /**
     * Constructor for unique id-based deletion
     *
     * @param uid unique id of the employee to delete
     */
    public DeleteCommand(UniqueId uid) {
        this.targetIndex = null; // Index is not used in this context
        this.targetName = null; // Name is not used in this context
        this.uid = uid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        LOGGER.log(Level.INFO, "Executing delete command");

        if (targetIndex != null) {
            // Index-based deletion logic remains the same
            return deleteByIndex(model);
        } else if (targetName != null && !targetName.trim().isEmpty()) {
            // Implement name-based deletion logic
            return deleteByName(model);
        } else if (uid != null) {
            // Implement unique id-based deletion logic
            return deleteByUid(model);
        } else {
            LOGGER.log(Level.WARNING, "Invalid command format");
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Deletes a employee by index
     *
     * @param model the model to execute the command
     * @return the result of the command
     * @throws CommandException if the index is invalid
     */
    private CommandResult deleteByIndex(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        requireNonNull(lastShownList, "Employee list is null");

        assert targetIndex != null;
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            LOGGER.log(Level.WARNING, "Invalid employee index");
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteEmployee(employeeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employeeToDelete)));
    }

    /**
     * Deletes an employee by name
     *
     * @param model the model to execute the command
     * @return the result of the command
     * @throws CommandException if the employee is not found
     */
    private CommandResult deleteByName(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        requireNonNull(lastShownList, "Employee list is null");

        List<Employee> employeesWithTargetName = new ArrayList<>();
        for (Employee employee : lastShownList) {
            if (employee.getName().fullName.equalsIgnoreCase(targetName)) {
                employeesWithTargetName.add(employee);
            }
        }

        if (employeesWithTargetName.size() > 1) {
            LOGGER.log(Level.WARNING, "Multiple employees found with name: " + targetName);
            throw new CommandException(MESSAGE_MULTIPLE_EMPLOYEES_FOUND);
        } else if (employeesWithTargetName.isEmpty()) {
            LOGGER.log(Level.WARNING, "No employee found with name: " + targetName);
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        } else {
            Employee employeeToDelete = employeesWithTargetName.get(0);
            model.deleteEmployee(employeeToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employeeToDelete)));
        }
    }

    /**
     * Deletes an employee by unique id
     *
     * @param model the model to execute the command
     * @return the result of the command
     * @throws CommandException if the employee is not found
     */
    private CommandResult deleteByUid(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        requireNonNull(lastShownList, "Employee list is null");

        for (Employee employee : lastShownList) {
            assert uid != null;
            if (employee.getUid().equals(uid)) {
                model.deleteEmployee(employee);
                return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employee)));
            }
        }

        LOGGER.log(Level.WARNING, "No employee found with UID: " + uid);
        throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        // Check if both commands have the same index
        boolean isIndexEqual = targetIndex != null
                && otherDeleteCommand.targetIndex != null
                && targetIndex.equals(otherDeleteCommand.targetIndex);

        // Check if both commands have the same UID
        boolean isUidEqual = uid != null
                && otherDeleteCommand.uid != null
                && uid.equals(otherDeleteCommand.uid);

        // Check if both commands have the same name
        boolean isNameEqual = targetName != null
                && otherDeleteCommand.targetName != null
                && targetName.equalsIgnoreCase(otherDeleteCommand.targetName);

        return isIndexEqual || isUidEqual || isNameEqual;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("targetName", targetName)
                .add("uid", uid)
                .toString();
    }
}
