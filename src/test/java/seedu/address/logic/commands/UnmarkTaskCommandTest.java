package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

public class UnmarkTaskCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_taskUnmarkedSuccessfully_success() throws Exception {
        Employee employee = new EmployeeBuilder().withUid("1").build();
        employee.addTask(new Description("Complete assignment"));
        model.addEmployee(employee);

        employee.markTask(0);

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(employee.getUid(), 1);

        CommandResult commandResult = unmarkTaskCommand.execute(model);

        assertEquals(String.format(UnmarkTaskCommand.MESSAGE_SUCCESS, employee.getTask(0)),
                commandResult.getFeedbackToUser());
        assertFalse(employee.getTask(0).isDone());
    }

    @Test
    public void execute_invalidEmployeeUid_throwsCommandException() {
        UniqueId invalidUid = new UniqueId("9999");
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(invalidUid, 1);

        assertThrows(CommandException.class, () -> unmarkTaskCommand.execute(model),
                Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
    }

    @Test
    public void constructor_invalidTaskNumber_throwsIllegalArgumentException() {
        UniqueId uid = new UniqueId("1");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new UnmarkTaskCommand(uid, -1),
                "Task number must be greater than zero."
        );

        assertEquals("Task number must be greater than zero.", exception.getMessage());
    }
}
