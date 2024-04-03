package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.task.Description;

public class MarkTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validUidAndTaskNumber_success() {
        Employee employeeToMark = model.getFilteredEmployeeList().get(0);
        employeeToMark.clearTasks();
        employeeToMark.addTask(new Description("Buy milk"));
        UniqueId uid = employeeToMark.getUid();
        int taskIndex = 1;

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(uid, taskIndex);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_SUCCESS, "[X] Buy milk");
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markTask(employeeToMark, taskIndex);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidUid_throwsCommandException() {
        UniqueId invalidUid = new UniqueId("-1");
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(invalidUid, 1);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_UID);
    }

    @Test
    public void execute_invalidTaskNumber_throwsCommandException() {
        Employee employeeToMark = model.getFilteredEmployeeList().get(0);
        UniqueId uid = employeeToMark.getUid();
        int invalidTaskNumber = 9999;

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(uid, invalidTaskNumber);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
