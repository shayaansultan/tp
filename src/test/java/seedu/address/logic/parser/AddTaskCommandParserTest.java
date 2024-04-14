package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.task.Description;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_validArgs_returnsAddTaskCommand() throws IllegalStateException, IllegalValueException {
        AddTaskCommand command = parser.parse("uid/1 Buy milk");
        assertEquals(new AddTaskCommand(new UniqueId(1), new Description("Buy milk")), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse("Buy milk"));
        assertThrows(ParseException.class, () -> parser.parse(""));
    }
}
