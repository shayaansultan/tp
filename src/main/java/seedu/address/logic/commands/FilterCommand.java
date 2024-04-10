package seedu.address.logic.commands;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;

/**
 * Filters the employee list based on the given criteria.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    private static final Logger LOGGER = Logger.getLogger(FilterCommand.class.getName());
    private final Predicate<Employee> predicate;
    private final String filterDescription;

    /**
     * Creates a FilterCommand to filter the employee list based on the given criteria.
     *
     * @param predicate The predicate to filter the employee list. Must not be null.
     * @param filterDescription The description of the filter criteria. Must not be null or empty.
     */
    public FilterCommand(Predicate<Employee> predicate, String filterDescription) {
        this.predicate = Objects.requireNonNull(predicate, "Predicate must not be null");
        this.filterDescription = Objects.requireNonNullElse(filterDescription, "Unknown criteria");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.log(Level.INFO, "Executing filter command: " + filterDescription);

        model.updateFilteredEmployeeList(predicate);
        if (model.getFilteredEmployeeList().isEmpty()) {
            LOGGER.log(Level.WARNING, "No employees found matching the criteria: " + filterDescription);
            throw new CommandException("No employees found matching the criteria: " + filterDescription);
        }

        return new CommandResult("Filtered list based on: " + filterDescription);
    }

    /**
     * Returns the predicate used to filter the employee list.
     * @return The predicate used to filter the employee list.
     */
    public Predicate<Employee> getPredicate() {
        return predicate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterCommand)) {
            return false;
        }
        FilterCommand that = (FilterCommand) other;
        return Objects.equals(predicate, that.predicate)
                && Objects.equals(filterDescription, that.filterDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate, filterDescription);
    }
}
