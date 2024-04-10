package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.task.Description;

/**
 * Adds a task to the todo list of an employee.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final UniqueId uid;
    private final Description description;

    /**
     * Creates an AddTaskCommand to add a task to the todo list of an employee.
     */
    public AddTaskCommand(UniqueId uid, Description description) {
        this.uid = uid;
        this.description = description;
    }

    /**
     * Adds a task to the todo list of an employee.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} representing the result of the command
     *         execution.
     * @throws CommandException If an error occurs during command execution.
     */
    private CommandResult addTask(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        Employee employee = null;

        for (Employee e : lastShownList) {
            assert uid != null;
            if ((e.getUid().getUidValue()).equals(uid.getUidValue())) {
                employee = e;
                break;
            }
        }

        if (employee == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
        }

        employee.addTask(description);
        model.updateFilteredEmployeeList(Model.PREDICATE_SHOW_ALL_EMPLOYEES);
        System.out.println("Task added: " + description + " to employee: " + employee.getName());
        return new CommandResult(String.format(MESSAGE_SUCCESS, description));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Employee> lastShownList = model.getFilteredEmployeeList();
        boolean employeeExists = lastShownList.stream()
                .anyMatch(employee -> employee.getUid().equals(uid));

        if (!employeeExists) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
        }

        try {
            return addTask(model);
        } catch (CommandException e) {
            return new CommandResult(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddTaskCommand
                        && uid.equals(((AddTaskCommand) other).uid)
                        && description.equals(((AddTaskCommand) other).description));
    }

    @Override
    public String toString() {
        return "Add task for employee with UID: " + uid + " and description: " + description;
    }
}
