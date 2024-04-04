package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

public class DeleteTaskCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_validArgs_deletesTask() {
        Employee employee = new EmployeeBuilder().withUid("1000").build();
        model.addEmployee(employee);
        employee.addTask(new Description("Buy milk"));

        DeleteTaskCommand command = new DeleteTaskCommand(employee.getUid(), 1);

        try {
            CommandResult result = command.execute(model);
            assertEquals(String.format(DeleteTaskCommand.MESSAGE_SUCCESS), result.getFeedbackToUser());
            assertFalse(employee.getTasks().contains(new Task(new Description("Buy milk"))));
        } catch (Exception e) {
            fail("Execution of command should not fail.");
        }
    }

    @Test
    public void execute_invalidEmployeeUid_throwsCommandException() {
        Model model = new ModelManager();

        UniqueId invalidUid = new UniqueId("-1");
        DeleteTaskCommand command = new DeleteTaskCommand(invalidUid, 1);

        assertFalse(model.getFilteredEmployeeList().stream()
                .anyMatch(employee -> employee.getUid().equals(invalidUid)));

        assertThrows(CommandException.class, () -> command.execute(model),
                Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
    }

    @Test
    public void equals() {
        DeleteTaskCommand command1 = new DeleteTaskCommand(new UniqueId("1"), 1);
        DeleteTaskCommand command2 = new DeleteTaskCommand(new UniqueId("1"), 1);
        DeleteTaskCommand command3 = new DeleteTaskCommand(new UniqueId("2"), 2);

        assertEquals(command1, command1); // same object -> returns true
        assertEquals(command1, command2); // same values -> returns true
        assertNotEquals(command1, command3); // different values -> returns false
        assertNotEquals(command1, 1); // different types -> returns false
        assertNotEquals(command1, null); // null -> returns false
    }
}
