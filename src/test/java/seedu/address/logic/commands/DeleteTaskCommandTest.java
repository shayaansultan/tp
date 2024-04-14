package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.employee.UniqueId;

public class DeleteTaskCommandTest {
    @BeforeEach
    public void setUp() {
        new ModelManager();
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
