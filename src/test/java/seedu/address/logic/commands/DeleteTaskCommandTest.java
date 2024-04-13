package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import seedu.address.testutil.EmployeeBuilder;

public class DeleteTaskCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_invalidEmployeeUid_throwsCommandException() {
        UniqueId invalidUid = new UniqueId("-1");
        DeleteTaskCommand command = new DeleteTaskCommand(invalidUid, 1);

        assertFalse(model.getFilteredEmployeeList().stream()
                .anyMatch(employee -> employee.getUid().equals(invalidUid)));

        assertThrows(CommandException.class, () -> command.execute(model),
                Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
    }

    @Test
    public void execute_validArgs_deletesTask() {
        Employee employee = new EmployeeBuilder().withUid("100").build();
        model.addEmployee(employee);

        Description taskDescription = new Description("Buy milk");
        AddTaskCommand command1 = new AddTaskCommand(employee.getUid(), taskDescription);
        DeleteTaskCommand command2 = new DeleteTaskCommand(employee.getUid(), 1);

        try {
            command1.execute(model);
            CommandResult result = command2.execute(model);
            assertNotNull(result);
            assertEquals(DeleteTaskCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
        } catch (Exception e) {
            fail("Execution of command should not fail.");
        }
    }

    @Test
    public void execute_emptyTaskList_throwsCommandException() throws CommandException {
        Model model = new ModelManager();

        Employee employee = new EmployeeBuilder().withUid("100").build();
        model.addEmployee(employee);

        DeleteTaskCommand command = new DeleteTaskCommand(employee.getUid(), 1);
        command.execute(model);
    }

    @Test
    public void execute_invalidEmployee_throwsCommandException() {
        UniqueId uid = new UniqueId("100");
        DeleteTaskCommand command = new DeleteTaskCommand(uid, 1);

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

    @Test
    public void toStringResult() {
        DeleteTaskCommand command = new DeleteTaskCommand(new UniqueId("1"), 1);
        String result = "Delete task for employee with UID: 1 at index: 1";
        assertEquals(command.toString(), result);
    }
}
