package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.testutil.EmployeeBuilder;


public class AddTaskCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_validArgs_addsTask() {
        Employee employee = new EmployeeBuilder().withUid("1000").build();
        model.addEmployee(employee);

        Description taskDescription = new Description("Buy milk");
        AddTaskCommand command = new AddTaskCommand(employee.getUid(), taskDescription);

        try {
            CommandResult result = command.execute(model);
            assertNotNull(result);
            assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, taskDescription), result.getFeedbackToUser());
            assertTrue(employee.getTasks().contains(new Task(taskDescription)));
        } catch (Exception e) {
            fail("Execution of command should not fail.");
        }
    }

    @Test
    public void execute_invalidEmployeeUid_throwsCommandException() {
        Model model = new ModelManager();

        UniqueId invalidUid = new UniqueId("-1");
        AddTaskCommand command = new AddTaskCommand(invalidUid, new Description("Buy milk"));

        assertFalse(model.getFilteredEmployeeList().stream()
                .anyMatch(employee -> employee.getUid().equals(invalidUid)));

        assertThrows(CommandException.class, () -> command.execute(model),
                Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
    }

    @Test
    public void equals() {
        AddTaskCommand command1 = new AddTaskCommand(new UniqueId("1"), new Description("Buy milk"));
        AddTaskCommand command2 = new AddTaskCommand(new UniqueId("1"), new Description("Buy milk"));
        AddTaskCommand command3 = new AddTaskCommand(new UniqueId("2"), new Description("Buy bread"));

        assertEquals(command1, command1); // same object -> returns true
        assertEquals(command1, command2); // same values -> returns true
        assertNotEquals(command1, command3); // different values -> returns false
        assertNotEquals(command1, 1); // different types -> returns false
        assertNotEquals(command1, null); // null -> returns false
    }

    @Test
    public void toStringResult() {
        AddTaskCommand command = new AddTaskCommand(new UniqueId("1"), new Description("Buy milk"));
        String result = "Add task for employee with UID: 1 and description: Buy milk";
        assertEquals(command.toString(), result);
    }
}
