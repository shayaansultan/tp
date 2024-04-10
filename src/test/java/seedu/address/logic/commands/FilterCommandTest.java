package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithEmployees;
import seedu.address.model.ModelStubWithNoEmployee;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandTest {

    @Test
    public void execute_validFilter_success() {
        Employee testEmployee = new EmployeeBuilder().withName("Alice").build();

        ModelStub modelStub = new ModelStubWithEmployees(Collections.singletonList(testEmployee));

        Predicate<Employee> predicate = employee -> employee.getName().equals(testEmployee.getName());
        FilterCommand filterCommand = new FilterCommand(predicate, "Name: Alice");

        assertDoesNotThrow(() -> filterCommand.execute(modelStub));
        assertEquals(Arrays.asList(testEmployee), modelStub.getFilteredEmployeeList());
    }

    @Test
    public void execute_invalidFilter_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithNoEmployee();
        Predicate<Employee> predicate = employee -> false;
        FilterCommand filterCommand = new FilterCommand(predicate, "Name: Nonexistent");

        assertThrows(CommandException.class, () -> filterCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Predicate<Employee> alicePredicate = employee -> employee.getName().equals("Alice");
        Predicate<Employee> bobPredicate = employee -> employee.getName().equals("Bob");

        FilterCommand filterAliceCommand = new FilterCommand(alicePredicate, "Name: Alice");
        FilterCommand filterAliceCommandCopy = new FilterCommand(alicePredicate, "Name: Alice");
        FilterCommand filterBobCommand = new FilterCommand(bobPredicate, "Name: Bob");

        // same object -> returns true
        assertEquals(filterAliceCommand, filterAliceCommand);

        // same values -> returns true
        assertEquals(filterAliceCommand, filterAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, filterAliceCommand);

        // null -> returns false
        assertNotEquals(null, filterAliceCommand);

        // different predicate -> returns false
        assertNotEquals(filterAliceCommand, filterBobCommand);
    }

    @Test
    public void hashCode_test() {
        Predicate<Employee> predicate = employee -> employee.getName().equals("Alice");
        FilterCommand filterCommand = new FilterCommand(predicate, "Name: Alice");
        FilterCommand filterCommandCopy = new FilterCommand(predicate, "Name: Alice");

        assertEquals(filterCommand.hashCode(), filterCommandCopy.hashCode());

        // Consistent across calls
        int hash1 = filterCommand.hashCode();
        int hash2 = filterCommand.hashCode();
        assertEquals(hash1, hash2);
    }
}
