package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModelManager;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.testutil.EmployeeBuilder;

public class AddTaskCommandTest {
    @Test
    public void execute_validArgs_addsTask() {
        ModelManager model = new ModelManager();
        Employee employee = new EmployeeBuilder().build();

        model.addEmployee(employee);
        AddTaskCommand command = new AddTaskCommand(new UniqueId("1000"), new Description("Buy milk"));

        try {
            CommandResult result = command.execute(model);
            assertTrue(employee.getTasks().contains(new Task(new Description("Buy milk"))));
            assertEquals("New task added: Buy milk", result.getFeedbackToUser());
        } catch (Exception e) {
            fail("Execution of command should not fail.");
        }
    }

    @Test
    public void equals() {
        AddTaskCommand command1 = new AddTaskCommand(new UniqueId("1"), new Description("Buy milk"));
        AddTaskCommand command2 = new AddTaskCommand(new UniqueId("1"), new Description("Buy milk"));
        AddTaskCommand command3 = new AddTaskCommand(new UniqueId("2"), new Description("Buy bread"));

        // same object -> returns true
        assertEquals(command1, command1);

        // same values -> returns true
        assertEquals(command1, command2);

        // different values -> returns false
        assertNotEquals(command1, command3);

        // different types -> returns false
        assertNotEquals(command1, 1);

        // null -> returns false
        assertNotEquals(command1, null);
    }
}
