package seedu.address.model.util;

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
 * Utility class for common command-related operations.
 * This class provides static methods used by various command classes
 * to perform operations that are common across multiple commands.
 */
public class CommandUtil {
    private static final Logger LOGGER = Logger.getLogger(CommandUtil.class.getName());

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
    public static Employee findEmployeeByUniqueId(Model model, UniqueId uid) throws CommandException {
        requireNonNull(model);
        requireNonNull(uid);

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
}
